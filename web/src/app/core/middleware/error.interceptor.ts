import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

import { MensagemService } from '@core/services/mensagem.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private mensagemService: MensagemService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const skipMensagem = req.headers.get('skip-error');
    if ((skipMensagem && skipMensagem == 'true')) {
      return next.handle(req).pipe();
    }

    return next.handle(req).pipe(
      catchError((erro: HttpErrorResponse) => {
        setTimeout(() => {
          console.log(erro);
          if (erro.status == 0) {
            !navigator.onLine ?
              this.mensagemService.notificarErro('Sem conexão com a Internet') :
              this.mensagemService.popupErro('Ocorreu um erro desconhecido');
          } else if (erro.status >= 400) {
            this.mensagemService.popupAlerta('', `${erro.error.message || erro.error[0].mensagem}`);
          } else if (erro.status >= 500) {
            this.mensagemService.popupErro('Nossos serviços tiveram um problema', 'Aguarde alguns instantes');
          }
        }, 200);

        return throwError(erro);
      })
    )
  }
}

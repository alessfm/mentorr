import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { MensagemMentoria } from '../models/mentoria.model';

@Injectable()
export class MensagensMentoriaService extends GenericService<MensagemMentoria> {

  constructor(http: HttpClient) {
    super('api/mentorias', http)
  }

  buscarTodas(idMentoria: number, loading?: Loading): Observable<MensagemMentoria[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get(`${this.api}/${idMentoria}/mensagens`)
      .pipe(this.configMapAndLoading(loading))
  }

  enviar(idMentoria: number, form: FormData, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/${idMentoria}/mensagens`, form)
      .pipe(this.configMapAndLoading(loading))
  }

}

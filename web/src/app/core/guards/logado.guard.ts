import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '../services/mensagem.service';

interface Payload {
  iss: string;
  sub: string;
  apelido: string;
  exp: number;
}

@Injectable()
export class LogadoGuard implements CanActivate {

  constructor(
    private router: Router,
    private cookiesService: CookiesService,
    private mensagemService: MensagemService
  ) { }

  canActivate() {
    const token = this.cookiesService.token;
    const payload = this.decodarToken(token);

    if (!payload) {
      this.router.navigate(['login']);
      this.mensagemService.notificarErro('', 'Você não está autenticado no Mentorr');
      return false;
    }

    if (new Date() > new Date(payload.exp * 1000)) {
      this.router.navigate(['login']);
      this.mensagemService.notificarAlerta('', 'A sua sessão expirou...');
      return false;
    }

    return true;
  }

  private decodarToken(token: string | null): Payload | null {
    try {
      token = token || '';
      const payload = token.split('.')[1]; //* header.payload.secret
      return JSON.parse(atob(payload));
    } catch (error) {
      console.log(error);
      return null;
    }
  }
}
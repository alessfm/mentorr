import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';

import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '../services/mensagem.service';

@Injectable()
export class RoleGuard implements CanActivate {

  constructor(
    private router: Router,
    private cookiesService: CookiesService,
    private mensagemService: MensagemService
  ) { }

  canActivate(rota: ActivatedRouteSnapshot) {
    const profile = this.cookiesService.profile;

    if (!profile) {
      this.router.navigate(['login']);
      this.mensagemService.notificarErro('', 'Você não está autenticado no Mentorr');
      return false;
    }

    if (profile != rota.data.profile) {
      this.router.navigate(['/']);
      this.mensagemService.notificarErro('', 'Você não tem permissão para acessar essa página');
      return false;
    }

    return true;
  }
}
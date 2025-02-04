import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { CookiesService } from '@core/services/cookies.service';

@Injectable()
export class LogadoGuard implements CanActivate {

  constructor(
    private router: Router,
    private cookiesService: CookiesService
  ) { }

  canActivate() {
    const temToken = Boolean(this.cookiesService.token);

    if (!temToken) {
      this.router.navigate(['login']);
    }

    return temToken;
  }
}
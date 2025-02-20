import { Injectable } from '@angular/core';
import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

import { CookiesService } from '@core/services/cookies.service';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  constructor(private cookiesService: CookiesService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const token = this.cookiesService.token;

    if (request.headers.get('skip-token')) {
      return next.handle(request);
    }

    if (token) {
      request = request.clone({
        setHeaders: {
          CacheControl: 'no-cache',
          Pragma: 'no-cache',
          Expires: 'Sat, 01 Jan 2000 00:00:00 GMT',
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request);
  }
}
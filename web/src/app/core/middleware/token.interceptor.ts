import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';

import { catchError } from 'rxjs/operators';
import { BehaviorSubject, Observable, throwError } from 'rxjs';

import { environment } from '@environments/environment';
import { CookiesService } from '@core/services/cookies.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  API_URL = environment.api;
  public user: Observable<any>;
  private userSubject: BehaviorSubject<any>;

  constructor(
    private router: Router,
    private httpClient: HttpClient,
    private cookiesService: CookiesService
  ) {
    this.userSubject = new BehaviorSubject<any>(null);
    this.user = this.userSubject.asObservable();
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const skipToken = request.headers.get('skip-token');
    if ((skipToken && skipToken == 'true')) {
      return next.handle(request).pipe();
    }

    return next.handle(request).pipe(catchError(err => {
      if ([401, 403].includes(err.status) && this.cookiesService.token) {
        this.httpClient
          .post<any>(`${this.API_URL}/refresh`, {}, { withCredentials: true, headers: this.getHeadersPularErro() })
          .subscribe();
        this.userSubject.next(null);
        this.router.navigate(['/login']);
      }

      const error = (err && err.error && err.error.message) || err.statusText;
      return throwError(error);
    }))
  }

  protected getHeadersPularErro(): HttpHeaders {
    return new HttpHeaders().append('skip-error', 'true');
  }
}

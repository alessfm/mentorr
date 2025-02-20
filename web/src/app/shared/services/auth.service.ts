import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Login, PosLogin } from '../models/auth.model';

@Injectable()
export class AuthService extends GenericService<any> {

  constructor(http: HttpClient) {
    super('login', http)
  }

  entrarNaConta(entity: Login, loading?: Loading): Observable<PosLogin> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}`, entity, { headers: this.getHeadersPular() })
      .pipe(this.configMapAndLoading(loading))
  }
}
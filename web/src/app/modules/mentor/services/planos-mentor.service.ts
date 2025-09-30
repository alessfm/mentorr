import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Plano } from '../models/plano.model';

@Injectable()
export class PlanosMentorService extends GenericService<Plano> {

  constructor(http: HttpClient) {
    super('api/mentor/planos', http)
  }

  salvarLote(entity: { planos: Plano[] }, loading?: Loading): Observable<Plano[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/lote`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

}

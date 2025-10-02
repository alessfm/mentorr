import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Mentor } from '../models/mentor.model';

@Injectable()
export class MentorService extends GenericService<Mentor> {

  constructor(http: HttpClient) {
    super('api/mentor', http)
  }

  buscarDados(loading?: Loading): Observable<Mentor> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get(`${this.api}`, { headers: this.getHeadersPularErro() })
      .pipe(this.configMapAndLoading(loading));
  }

  alterarStatus(loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .put(`${this.api}/status`, null)
      .pipe(this.configMapAndLoading(loading));
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Horario } from '../models/horario.model';

@Injectable()
export class HorariosMentorService extends GenericService<Horario> {

  constructor(http: HttpClient) {
    super('api/mentor/horarios', http)
  }

  salvarLote(entity: { horarios: Horario[] }, loading?: Loading): Observable<Horario[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/lote`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

}

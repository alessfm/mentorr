import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Horario } from '../models/horario.model';

@Injectable()
export class HorariosService extends GenericService<Horario> {

  constructor(http: HttpClient) {
    super('api/mentores', http)
  }

  buscarTodos(idMentor: number, loading?: Loading): Observable<Horario[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<Horario[]>(`${this.api}/${idMentor}/horarios`)
      .pipe(this.configMapAndLoading(loading));
  }

  buscarPorId(idMentor: number, id: number, loading?: Loading): Observable<Horario> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<Horario>(`${this.api}/${idMentor}/horarios/${id}`)
      .pipe(this.configMapAndLoading(loading));
  }

  salvar(idMentor: number, entity: Horario, loading?: Loading): Observable<Horario> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/${idMentor}/horarios`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  salvarLote(idMentor: number, entity: { horarios: Horario[] }, loading?: Loading): Observable<Horario[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/${idMentor}/horarios/lote`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  atualizar(idMentor: number, id: number, entity: Horario, loading?: Loading): Observable<Horario> {
    this.startLoading(loading);
    return this.getHttpClient()
      .put(`${this.api}/${idMentor}/horarios/${id}`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  excluir(idMentor: number, id: number, entity?: Horario, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .request('DELETE', `${this.api}/${idMentor}/horarios/${id}`, { body: entity })
      .pipe(this.configMapAndLoading(loading));
  }

}
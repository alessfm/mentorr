import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Plano } from '../models/plano.model';

@Injectable()
export class PlanosService extends GenericService<Plano> {

  constructor(http: HttpClient) {
    super('api/mentores', http)
  }

  buscarTodos(idMentor: number, loading?: Loading): Observable<Plano[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<Plano[]>(`${this.api}/${idMentor}/planos`)
      .pipe(this.configMapAndLoading(loading));
  }

  buscarPorId(idMentor: number, id: number, loading?: Loading): Observable<Plano> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<Plano>(`${this.api}/${idMentor}/planos/${id}`)
      .pipe(this.configMapAndLoading(loading));
  }

  salvar(idMentor: number, entity: Plano, loading?: Loading): Observable<Plano> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/${idMentor}/planos`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  salvarLote(idMentor: number, entity: { planos: Plano[] }, loading?: Loading): Observable<Plano[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/${idMentor}/planos/lote`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  atualizar(idMentor: number, id: number, entity: Plano, loading?: Loading): Observable<Plano> {
    this.startLoading(loading);
    return this.getHttpClient()
      .put(`${this.api}/${idMentor}/planos/${id}`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  excluir(idMentor: number, id: number, entity?: Plano, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .request('DELETE', `${this.api}/${idMentor}/planos/${id}`, { body: entity })
      .pipe(this.configMapAndLoading(loading));
  }

}
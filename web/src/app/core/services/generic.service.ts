import { HttpClient, HttpHeaders } from '@angular/common/http';

import { finalize } from 'rxjs/operators';
import { MonoTypeOperatorFunction, Observable, pipe, UnaryFunction } from 'rxjs';

import { configMap } from '@core/utils/config-map';
import { AddHttpParams } from '@core/utils/add-http-params';
import { environment } from '@environments/environment';

import { Loading } from '@core/models/loading.model';
import { Paginacao } from '@shared/models/paginacao.model';

export abstract class GenericService<T> {

  private _api = environment.api;

  constructor(
    private controller: string,
    private httpClient: HttpClient
  ) {
    this.api = this.controller;
  }

  /*/ Funções CRUD /*/

  get(loading?: Loading): Observable<T> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<T>(`${this._api}`)
      .pipe(this.configMapAndLoading(loading));
  }

  getWithParams(filter?: {}, loading?: Loading): Observable<Paginacao<T>> {
    this.startLoading(loading);
    const params = new AddHttpParams(filter).createParams();
    return this.getHttpClient()
      .get<T[]>(`${this._api}/busca`, { params })
      .pipe(this.configMapAndLoading(loading));
  }

  getById(id: number, loading?: Loading): Observable<T> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<T>(`${this._api}/${id}`)
      .pipe(this.configMapAndLoading(loading));
  }

  save(entity: T, loading?: Loading): Observable<T> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this._api}`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  update(entity: T, loading?: Loading): Observable<T> {
    this.startLoading(loading);
    return this.getHttpClient()
      .put(`${this._api}`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  updateById(id: number, entity: T, loading?: Loading): Observable<T> {
    this.startLoading(loading);
    return this.getHttpClient()
      .put(`${this._api}/${id}`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

  delete(loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .delete(`${this._api}`)
      .pipe(this.configMapAndLoading(loading));
  }

  deleteById(id: number, entity?: T, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .request('DELETE', `${this._api}/${id}`, { body: entity })
      .pipe(this.configMapAndLoading(loading));
  }

  /*/ Headers /*/

  protected getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Set-Cookie': 'HttpOnly;Secure;SameSite=Strict',
    });
  }

  protected getHeadersCors(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Access-Control-Allow-Origin': '/',
      'skip-error': 'true'
    });
  }

  protected getHeadersMulti(): HttpHeaders {
    return new HttpHeaders({
      'Accept': '*/*',
    });
  }

  protected getHeadersPDF(): HttpHeaders {
    return new HttpHeaders().set('Accept', 'application/pdf');
  }

  protected getHeadersExcel(): HttpHeaders {
    return new HttpHeaders().set('Accept', 'application/octet-stream');
  }

  protected getHeadersPular(): HttpHeaders {
    return new HttpHeaders().append('skip-token', 'true');
  }

  protected getHeadersPularErro(): HttpHeaders {
    return new HttpHeaders().append('skip-error', 'true');
  }

  /*/ Carregamento /*/

  protected configMapAndLoading(loading?: Loading): UnaryFunction<Observable<any>, Observable<any>> {
    return pipe(
      configMap(),
      this.stopLoading(loading)
    )
  }

  protected startLoading(loading?: Loading): void {
    loading = this.handlerLoading(loading);
    if (loading.startLoading) {
      loading.load = true;
    }
  }

  protected stopLoading(loading?: Loading): MonoTypeOperatorFunction<any> {
    loading = this.handlerLoading(loading);
    return loading.stopLoading ? finalize(() => loading ? loading.load = false : {}) : pipe();
  }

  private handlerLoading(loading?: Loading): Loading {
    if (loading == null || loading == undefined) {
      loading = { startLoading: false, stopLoading: false };
    }
    if (loading.startLoading == undefined) {
      loading.startLoading = false;
    }
    if (loading.stopLoading == undefined) {
      loading.stopLoading = false;
    }
    return loading;
  }

  /*/ Gets e Sets /*/

  protected getHttpClient(): HttpClient {
    return this.httpClient;
  }

  protected set api(controller: string) {
    this._api = `${this._api}/${controller}`;
  }

  protected get api(): string {
    return this._api;
  }
}

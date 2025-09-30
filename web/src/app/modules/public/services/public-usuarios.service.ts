import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Usuario } from '@shared/models/usuario.model';

@Injectable()
export class PublicUsuariosService extends GenericService<Usuario> {

  constructor(http: HttpClient) {
    super('api/public/usuarios', http)
  }

  criarConta(tipo: string, entity: Usuario, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}/${tipo}`, entity)
      .pipe(this.configMapAndLoading(loading));
  }

}

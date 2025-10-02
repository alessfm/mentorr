import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Mentoria } from '../models/mentoria.model';

@Injectable()
export class MentoriasAlunoService extends GenericService<Mentoria> {

  constructor(http: HttpClient) {
    super('api/aluno/mentorias', http)
  }

  solicitar(form: FormData, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .post(`${this.api}`, form)
      .pipe(this.configMapAndLoading(loading))
  }

}

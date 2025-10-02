import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Mentoria } from '../models/mentoria.model';

@Injectable()
export class MentoriasMentorService extends GenericService<Mentoria> {

  constructor(http: HttpClient) {
    super('api/mentor/mentorias', http)
  }

  alterarStatus(idMentoria: number, form: FormData, loading?: Loading): Observable<void> {
    this.startLoading(loading);
    return this.getHttpClient()
      .put(`${this.api}/${idMentoria}`, form)
      .pipe(this.configMapAndLoading(loading))
  }

}

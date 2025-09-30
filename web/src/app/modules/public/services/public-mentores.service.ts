import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { MentorBusca, MentorPublic } from '../models/mentor-public.model';
import { TotaisMentores } from '../models/totais-mentores.model';

@Injectable()
export class PublicMentoresService extends GenericService<MentorBusca> {

  constructor(http: HttpClient) {
    super('api/public/mentores', http)
  }

  buscarTotais(loading?: Loading): Observable<TotaisMentores> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get(`${this.api}/totais`)
      .pipe(this.configMapAndLoading(loading));
  }

  buscarDestaques(loading?: Loading): Observable<MentorBusca[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get(`${this.api}/destaques`)
      .pipe(this.configMapAndLoading(loading));
  }

  buscarPorApelido(apelido: string, loading?: Loading): Observable<MentorPublic> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get(`${this.api}/perfil/${apelido}`)
      .pipe(this.configMapAndLoading(loading));
  }

}

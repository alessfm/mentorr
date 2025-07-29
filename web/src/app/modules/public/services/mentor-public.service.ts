import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { MentorBusca, MentorPublic } from '../models/mentor-public.model';
import { TotaisMentores } from '../models/totais-mentores.model';

@Injectable()
export class MentorPublicService extends GenericService<MentorBusca> {

  constructor(http: HttpClient) {
    super('api/mentores', http)
  }

  buscarTotais(loading?: Loading): Observable<TotaisMentores> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<TotaisMentores>(`${this.api}/totais`)
      .pipe(this.configMapAndLoading(loading));
  }

  buscarDestaques(loading?: Loading): Observable<MentorBusca[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<MentorBusca[]>(`${this.api}/destaque`)
      .pipe(this.configMapAndLoading(loading));
  }

  buscarPorApelido(apelido: string, loading?: Loading): Observable<MentorPublic> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<MentorPublic>(`${this.api}/apelido/${apelido}`, { headers: this.getHeadersPularErro() })
      .pipe(this.configMapAndLoading(loading));
  }
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Tag } from '../models/tag.model';

@Injectable()
export class TagService extends GenericService<Tag> {

  constructor(http: HttpClient) {
    super('api/tags', http)
  }

  buscarDestaques(loading?: Loading): Observable<Tag[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get<Tag[]>(`${this.api}/destaque`)
      .pipe(this.configMapAndLoading(loading));
  }
}
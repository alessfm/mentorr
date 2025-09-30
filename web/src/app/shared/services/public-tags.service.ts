import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { GenericService } from '@core/services/generic.service';

import { Loading } from '@core/models/loading.model';
import { Tag } from '@shared/models/tag.model';

@Injectable()
export class PublicTagsService extends GenericService<Tag> {

  constructor(http: HttpClient) {
    super('api/public/tags', http)
  }

  buscarDestaques(loading?: Loading): Observable<Tag[]> {
    this.startLoading(loading);
    return this.getHttpClient()
      .get(`${this.api}/destaques`)
      .pipe(this.configMapAndLoading(loading));
  }

}

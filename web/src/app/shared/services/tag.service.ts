import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { GenericService } from '@core/services/generic.service';
import { Tag } from '../models/tag.model';

@Injectable()
export class TagService extends GenericService<Tag> {

  constructor(http: HttpClient) {
    super('api/tags', http)
  }
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { GenericService } from '@core/services/generic.service';

import { Tag } from '@shared/models/tag.model';

@Injectable()
export class TagsMentorService extends GenericService<Tag> {

  constructor(http: HttpClient) {
    super('api/mentor/tags', http)
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { GenericService } from '@core/services/generic.service';
import { Usuario } from '../models/usuario.model';

@Injectable()
export class UsuarioService extends GenericService<Usuario> {

  constructor(http: HttpClient) {
    super('api/usuario', http)
  }
}

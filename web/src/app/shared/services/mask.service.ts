import { Injectable } from '@angular/core';

@Injectable()
export class MaskService {

  celular(): string {
    return '0 0000-0000';
  }

  celularDDD(): string {
    return '(00) 0 0000-0000';
  }

  telefoneFixo(): string {
    return '0000-0000';
  }

  private validarNull(valor: any): boolean {
    return valor === '' || valor === 'null' || valor === null || valor === undefined;
  }

}

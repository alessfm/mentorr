import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup } from '@angular/forms';

import { UtilInterface } from '@core/interfaces/util.interface';

@Injectable({
  providedIn: 'root'
})
export class UtilService implements UtilInterface {

  constructor(
    private router: Router,
    private location: Location
  ) { }

  voltar(): void {
    this.location.back();
  }

  redirecionar(link: string): void {
    this.router.navigate([link]);
  }

  recarregar(): void {
    location.reload();
  }

  navegarAoElemento(selector: string) {
    const elemento = document.querySelector(selector);
    elemento ? elemento.scrollIntoView({ behavior: 'smooth' }) : null;
  }

  verificarForm(form: FormGroup): FormGroup {
    for (const c in form.controls) {
      form.controls[c].markAsDirty();
    }
    return form;
  }

  desabilitarCampos(form: FormGroup, reset: boolean, campos: string[]): void {
    campos.forEach((c) => {
      form.get(c)?.disable();

      if (reset) {
        form.get(c)?.reset();
      }
    });
  }

  habilitarCampos(form: FormGroup, campos: string[]): void {
    campos.forEach((c) => form.get(c)?.enable());
  }
}
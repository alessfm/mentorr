import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor(
    private router: Router,
    private location: Location
  ) { }

  /*/ DOM /*/

  /**
   * @description Retorna a página anterior
   */
  voltar(): void {
    this.location.back();
  }

  /**
   * @description Redireciona para a página desejada
   */
  redirecionar(link: string, params?: {}): void {
    this.router.navigate([link], { queryParams: params });
  }

  /**
   * @description Recarrega a página atual
   */
  recarregar(): void {
    location.reload();
  }

  /**
   * @description Desliza a tela até o elemento desejado
   */
  navegarAoElemento(selector: string): void {
    const elemento = document.querySelector(selector);

    if (elemento) {
      elemento.scrollIntoView({ behavior: 'smooth' });
    }
  }

  /*/ FORMS /*/

  /**
   * @description
   * Valida todos os campos do formulário, exceto:
   * - Os que estão `disabled`
   * - Os que estão num `FormArray`
   * @returns Marca os campos inválidos em vermelho.
   */
  verificarForm(form: FormGroup): void {
    const camposInvalidos: string[] = [];

    for (const c in form.controls) {
      if (form.controls[c]) {
        form.controls[c].markAsTouched();

        if (form.controls[c].invalid) {
          camposInvalidos.push(c);
        }
      }
    }

    if (camposInvalidos.length) {
      console.log(camposInvalidos);
    }
  }

  /**
   * @description Desabilita campos do formulário
   * @example this.utilService.desabilitarCampos(this.form, Object.keys(this.form.controls));
   */
  desabilitarCampos(form: FormGroup, campos: string[]): void {
    campos.forEach(c => form.get(c)?.disable());
  }

  /**
   * @description Habilita campos do formulário
   * @example this.utilService.habilitarCampos(this.form, ['campo1', 'campo2']);
   */
  habilitarCampos(form: FormGroup, campos: string[]): void {
    campos.forEach(c => form.get(c)?.enable());
  }

  /**
   * @description Verifica se o valor é nulo ou vazio
   */
  validarNull(valor: any): boolean {
    return valor === '' || valor === 'null' || valor === null || valor === undefined;
  }
}
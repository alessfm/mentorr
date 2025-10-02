import { Injectable } from '@angular/core';
import { AbstractControl, FormGroup, ValidationErrors } from '@angular/forms';

import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  /**
   * @description
   * Valida todos os campos do formulário, exceto:
   * - Os que estão `disabled`
   * @returns
   * - Se válido, a função desejada.
   * - Se inválido, um popup de erro + marca os campos inválidos em vermelho.
   * @example this.utilFormService.validarFormEChamarFuncao(this.form)(this.salvar());
   */
  validarFormEChamarFuncao(form: FormGroup) {
    const erros = this.validarForm(form);

    if (erros.length) {
      console.log(erros);
    }

    if (form.invalid) {
      setTimeout(() =>
        Swal.fire(
          'Formulário inválido',
          'Verifique os campos destacados em vermelho',
          'warning'
        ), 100);
      throw new Error('Formulário inválido');
    }

    return function (fn: any) {
      return fn;
    }
  }

  private validarForm(form: FormGroup, nomeForm?: string): string[] {
    const erros = this.buscarErros(form.controls, nomeForm);

    if (form.errors) {
      erros.push(this.tratarErro(form.errors, 'form'));
    }

    return erros;
  }

  private buscarErros(controls: any, nomeForm?: string): string[] {
    let erros: string[] = [];

    for (const c in controls) {
      const nomeControl = nomeForm ? `${nomeForm}.${c}` : c; // Exemplo: id, lista[0].id
      const erro = this.validarControl(controls[c], nomeControl);

      if (!!erro) {
        erros.push(erro);
      }

      // FormArray
      if (controls[c].controls) {
        for (const cc in controls[c].controls) {
          const nomeFormArray = `${c}[${cc}]`; // Exemplo: lista[0]
          const errosFormArray = this.validarForm(controls[c].controls[cc], nomeFormArray);

          if (errosFormArray) {
            erros = erros.concat(errosFormArray);
          }
        }
      }
    }

    return erros;
  }

  private validarControl(control: AbstractControl, nomeControl: string): string | undefined {
    if (control) {
      control.markAsTouched();

      if (control.invalid && control.errors) {
        return this.tratarErro(control.errors, nomeControl);
      }
    }

    return undefined;
  }

  private tratarErro(erro: ValidationErrors, nomeCampo: string): string {
    switch (true) {
      case erro.required:
        return `${nomeCampo} é obrigatório(a)`;
      case erro.pattern != null:
        return `${nomeCampo} deve seguir o padrão`;
      case erro.mask != null:
        return `${nomeCampo} deve seguir o padrão(${erro.mask.requiredMask})`;
      case erro.max != null:
        return `${nomeCampo} deve ser menor que ${erro.max.max}`;
      case erro.min != null:
        return `${nomeCampo} deve ser maior que ${erro.min.min}`;
      case erro.maxlength != null:
        return `${nomeCampo} deve ter menos de ${erro.maxlength.requiredLength} caracteres`;
      case erro.minlength != null:
        return `${nomeCampo} deve ter mais de ${erro.minlength.requiredLength} caracteres`;
      case erro.inicioMaiorFim != null:
        return `${erro.inicioMaiorFim.inicio} deve ser menor que ${erro.inicioMaiorFim.fim}`;
      default:
        return `${nomeCampo} está inválido`;
    }
  }

  /**
   * @description Desabilita campos do formulário
   * @example this.utilFormService.desabilitarCampos(this.form, Object.keys(this.form.controls));
   */
  desabilitarCampos(form: FormGroup, campos: string[], resetar?: boolean): void {
    campos.forEach(c => {
      form.get(c)?.disable();

      if (resetar) {
        form.get(c)?.reset();
      }
    });
  }

  /**
   * @description Habilita campos do formulário
   * @example this.utilFormService.habilitarCampos(this.form, ['campo1', 'campo2']);
   */
  habilitarCampos(form: FormGroup, campos: string[]): void {
    campos.forEach(c => form.get(c)?.enable());
  }

}

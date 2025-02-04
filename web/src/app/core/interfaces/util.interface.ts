import { FormGroup } from '@angular/forms';

export interface UtilInterface {

  /**
   * @description Volta para a página anterior
   */
  voltar(): void;

  /**
   * @description Vai para a página desejada
   * @example redirecionar('/home')
   */
  redirecionar(link: string): void;

  /**
   * @description Recarrega a página atual
   */
  recarregar(): void;

  /**
   * @description Scroll na tela até o elemento
   * @example setTimeout(() => this.utilService.navegarAoElemento('table'), 250);
   */
  navegarAoElemento(seletor: string): void;

  /**
   * @description Toca todos os campos de um formulário, se algum estiver inválido, ficará em vermelho
   */
  verificarForm(form: FormGroup): FormGroup;

  /**
   *
   * @description Desabilita campos de um formulário e os reseta, se pedido
   * @example desabilitarCampos(this.form, false, Object.keys(this.form.controls));
   */
  desabilitarCampos(form: FormGroup, reset: boolean, campos: string[]): void;

  /**
   * @description Habilita campos de um formulário
   * @example habilitarCampos(this.form, true, ['senha']);
   */
  habilitarCampos(form: FormGroup, campos: string[]): void;
}
export interface Loading {
  startLoading?: boolean;
  stopLoading?: boolean;
  load?: boolean;
}

export class Loading {
  /**
   * @description Cria uma instância de carregamento, para informar se está sendo carregada ou não.
   * @param {boolean} init Dizer se deve iniciar o carregamento(quando o componente for chamado) ou não.
   * @example Use 'loading.load' para ver se ele está carregando
   */
  constructor(init?: boolean) {
    this.startLoading = true;
    this.stopLoading = true;
    this.load = init;
  }
}
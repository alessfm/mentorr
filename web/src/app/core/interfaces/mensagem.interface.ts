import { Mensagem } from '@core/models/mensagem.model';

export interface MensagemInterface {

  /*/ Mensagens /*/

  /**
   * @description Retorna mensagem de Sucesso (✅) genérica
   * @example popupSucesso('Arquivo adicionado!', 'Iremos validá-lo nos próximos dias')
   * @param {string} titulo - Título, curto e preciso (2 linhas no máximo)
   * @param {string} corpo - Descrição, até 64 caracteres é o recomendado (3 linhas no máximo)
   */
  popupSucesso(titulo?: string, corpo?: string): void;

  /**
   * @description Retorna mensagem de Alerta (⚠️) genérica
   * @example popupAlerta('Espere!', 'Ao sair da eleição, os dados fornecidos serão perdidos')
   * @param {string} titulo - Título, curto e preciso (2 linhas no máximo)
   * @param {string} corpo - Descrição, até 64 caracteres é o recomendado (3 linhas no máximo)
   */
  popupAlerta(titulo?: string, corpo?: string): void;

  /**
   * @description Retorna mensagem de Erro (❌) genérica
   * @example popupError('Ocorreu um erro desconhecido :(')
   * @param {string} titulo - Título, curto e preciso (2 linhas no máximo)
   * @param {string} corpo - Descrição, até 64 caracteres é o recomendado (3 linhas no máximo)
   */
  popupErro(titulo?: string, corpo?: string): void;

  /**
   * @description Retorna mensagem de Informação (🆗) genérica
   * @example popupInfo('Trocando de município...', 'Aguarde um instante')
   * @param {string} titulo - Título, curto e preciso (2 linhas no máximo)
   * @param {string} corpo - Descrição, até 64 caracteres é o recomendado (3 linhas no máximo)
   */
  popupInfo(titulo?: string, corpo?: string): void;

  /**
   * @description Popup de alerta sobre as Senhas Diferentes
   * @example if (!this.compararSenha) return this.mensagemService.popupSenhasDiferentes();
   */
  popupSenhasDiferentes(): void;

  /*/ Notificações /*/

  /**
   * @description Retorna notificação de Sucesso (✅) genérica
   * @example this.mensagemService.notificarSucesso('Notificações ativadas!')
   * @param {string} titulo - Título, curto e preciso (1 linha)
   * @param {string} corpo - Descrição, até 40 caracteres é o recomendado (2 linhas)
   */
  notificarSucesso(titulo?: string, corpo?: string): void;

  /**
   * @description Retorna notificação de Alerta (⚠️) genérica
   * @example this.mensagemService.notificarAlerta('Ops!', 'Sua conexão está instável')
   * @param {string} titulo - Título, curto e preciso (1 linha)
   * @param {string} corpo - Descrição, até 40 caracteres é o recomendado (2 linhas)
   */
  notificarAlerta(titulo?: string, corpo?: string): void;

  /**
   * @description Retorna notificação de Erro (❌) genérica
   * @example this.mensagemService.notificarError('Ocorreu um erro desconhecido :(')
   * @param {string} titulo - Título, curto e preciso (1 linha)
   * @param {string} corpo - Descrição, até 40 caracteres é o recomendado (2 linhas)
   */
  notificarErro(titulo?: string, corpo?: string): void;

  /**
   * @description Retorna notificação de Informação (🆗) genérica
   * @example this.mensagemService.notificarInfo('Novo Contracheque disponível!')
   * @param {string} titulo - Título, curto e preciso (1 linha)
   * @param {string} corpo - Descrição, até 40 caracteres é o recomendado (2 linhas)
   */
  notificarInfo(titulo?: string, corpo?: string): void;

  /*/ Confirmações /*/

  /**
   * @description Retorna confirmação de Sucesso (✅) genérica
   * @example confirmacaoSucesso({ titulo: 'Cadastro realizado!', textoSim: 'Entrar na conta', textoNao: 'OK, fechar' }, () => this.logar())
   * @param {Mensagem} mensagem - Título, Descrição, Texto do botão que continua e cancela
   * @param {Function} acaoSim - Função chamada pelo botão que continua
   * @param {Function} acaoNao - Função chamada pelo botão que cancela
   */
  confirmacaoSucesso(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void;

  /**
   * @description Retorna confirmação de Alerta (⚠️) genérica
   * @example confirmacaoAlerta({ titulo: 'Deseja confirmar?', descricao: 'Após sair da tela...' }, () => this.voltarPaginaInicial())
   * @param {Mensagem} mensagem - Título, Descrição, Texto do botão que continua e cancela
   * @param {Function} acaoSim - Função chamada pelo botão que continua
   * @param {Function} acaoNao - Função chamada pelo botão que cancela
   */
  confirmacaoAlerta(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void;

  /**
   * @description Retorna confirmação de Informação (🆗) genérica
   * @example confirmacaoInfo({ titulo: 'Nova versão disponível!', textoSim: 'Atualizar' }, () => this.atualizarVersao(), () => this.lembrarDepois())
   * @param {Mensagem} mensagem - Título, Descrição, Texto do botão que continua e cancela
   * @param {Function} acaoSim - Função chamada pelo botão que continua
   * @param {Function} acaoNao - Função chamada pelo botão que cancela
   */
  confirmacaoInfo(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void;
}

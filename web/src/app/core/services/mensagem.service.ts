import { Injectable } from '@angular/core';

import Swal from 'sweetalert2';
import { MensagemInterface } from '../interfaces/mensagem.interface';

import { Mensagem } from '../models/mensagem.model';
import { TipoMensagem } from '../enums/tipo-mensagem.enum';

@Injectable({
  providedIn: 'root'
})
export class MensagemService implements MensagemInterface {

  /*/ Popups /*/

  private popup(mensagem: Mensagem): void {
    setTimeout(() => Swal.fire(mensagem.titulo, mensagem.corpo, mensagem.tipo), 100);
  }

  popupSucesso(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Sucesso!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.popup({ titulo, corpo, tipo: TipoMensagem.SUCESSO });
  }

  popupAlerta(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Alerta!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.popup({ titulo, corpo, tipo: TipoMensagem.ALERTA });
  }

  popupErro(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Erro!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.popup({ titulo, corpo, tipo: TipoMensagem.ERRO });
  }

  popupInfo(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Fique atento!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.popup({ titulo, corpo, tipo: TipoMensagem.INFO });
  }

  popupSenhasDiferentes(): void {
    const titulo = 'As senhas estão diferentes';
    const corpo = 'Clique no "Mostrar Senha" para ver o que digitou';

    this.popup({ titulo, corpo, tipo: TipoMensagem.ALERTA });
  }

  popupFormularioInvalido(): void {
    const titulo = 'Formulário inválido';
    const corpo = 'Preencha os campos obrigatórios e verifique os valores digitados';

    this.popup({ titulo, corpo, tipo: TipoMensagem.ALERTA });
  }

  /*/ Notificações /*/

  private notificacao(mensagem: Mensagem): void {
    setTimeout(() => Swal.fire({
      title: mensagem.titulo,
      text: mensagem.corpo,
      icon: mensagem.tipo,
      toast: true,
      timer: 5000,
      timerProgressBar: true,
      position: 'top-right',
      showConfirmButton: false,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    }), 100);
  }

  notificarSucesso(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Sucesso!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.notificacao({ titulo, corpo, tipo: TipoMensagem.SUCESSO });
  }

  notificarAlerta(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Alerta!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.notificacao({ titulo, corpo, tipo: TipoMensagem.ALERTA });
  }

  notificarErro(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Erro!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.notificacao({ titulo, corpo, tipo: TipoMensagem.ERRO });
  }

  notificarInfo(titulo?: string, corpo?: string): void {
    if (!titulo) {
      titulo = 'Fique atento!';
    }
    if (!corpo) {
      corpo = '';
    }

    this.notificacao({ titulo, corpo, tipo: TipoMensagem.INFO });
  }

  /*/ Confirmações /*/

  private confirmacao(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void {
    Swal.fire({
      title: mensagem.titulo,
      html: mensagem.corpo,
      icon: mensagem.tipo,
      showCancelButton: Boolean(mensagem.textoNao),
      cancelButtonText: mensagem.textoNao,
      confirmButtonText: mensagem.textoSim,
      confirmButtonColor: mensagem.tipo == 'warning' ? '#D33' : '#026df9',
      allowOutsideClick: false,
      allowEscapeKey: false,
    }).then(result => {
      if (result.isConfirmed) {
        acaoSim();
      } else if (acaoNao) {
        acaoNao();
      }
    });
  }

  confirmacaoSucesso(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void {
    if (!mensagem.titulo) {
      mensagem.titulo = 'Está certo disso?';
    }
    if (!mensagem.corpo) {
      mensagem.corpo = '';
    }
    if (!mensagem.textoSim) {
      mensagem.textoSim = 'Sim';
    }
    if (!mensagem.textoNao) {
      mensagem.textoNao = 'Não';
    }

    this.confirmacao({ ...mensagem, tipo: TipoMensagem.SUCESSO }, acaoSim, acaoNao);
  }

  confirmacaoAlerta(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void {
    if (!mensagem.titulo) {
      mensagem.titulo = 'Está certo disso?';
    }
    if (!mensagem.corpo) {
      mensagem.corpo = '';
    }
    if (!mensagem.textoSim) {
      mensagem.textoSim = 'Sim';
    }
    if (!mensagem.textoNao) {
      mensagem.textoNao = 'Não';
    }

    this.confirmacao({ ...mensagem, tipo: TipoMensagem.ALERTA }, acaoSim, acaoNao);
  }

  confirmacaoInfo(mensagem: Mensagem, acaoSim: Function, acaoNao?: Function): void {
    if (!mensagem.titulo) {
      mensagem.titulo = 'Importante!';
    }
    if (!mensagem.corpo) {
      mensagem.corpo = '';
    }
    if (!mensagem.textoSim) {
      mensagem.textoSim = 'Entendi';
    }
    if (!mensagem.textoNao) {
      mensagem.textoNao = null!;
    }

    this.confirmacao({ ...mensagem, tipo: TipoMensagem.INFO }, acaoSim, acaoNao);
  }
}
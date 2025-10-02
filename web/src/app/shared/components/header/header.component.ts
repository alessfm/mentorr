import { Component } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
import { DOMService } from '@core/services/dom.service';
import { MensagemService } from '@core/services/mensagem.service';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  carregar = new Loading();
  mostrarDrop = false;

  constructor(
    private cookiesService: CookiesService,
    private mensagemService: MensagemService,
    private domService: DOMService
  ) { }

  irParaCadastro(): void {
    this.domService.redirecionar('/cadastro/aluno');
  }

  deslogar(): void {
    this.mensagemService.confirmacaoAlerta({ titulo: 'Deseja sair da conta?' }, () => {
      sessionStorage.clear();
      this.domService.redirecionar('entrar');
    });
  }

  get usuario() {
    return this.cookiesService.usuario;
  }

  get profile() {
    return this.cookiesService.profile;
  }

}

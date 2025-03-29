import { Component, OnInit } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '@core/services/mensagem.service';
import { TagService } from '@shared/services/tag.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';
import { Paginacao } from '@shared/models/paginacao.model';
import { Tag } from '@shared/models/tag.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  carregar = new Loading();
  mostrarDrop = false;
  tags: Paginacao<Tag> = {
    lista: [],
    pagina: 1,
    totalPaginas: 0,
    totalRegistros: 0
  };

  constructor(
    private cookiesService: CookiesService,
    private mensagemService: MensagemService,
    private tagService: TagService,
    private utilService: UtilService
  ) { }

  ngOnInit(): void {
    const params = { pagina: 1, totalPorPagina: 10 };
    this.tagService.getWithParams(params, this.carregar).subscribe(_ => this.tags = _);
  }

  irParaCadastro(): void {
    this.utilService.redirecionar('/cadastro/aluno');
  }

  buscarMentoresPorTag(idTag: number): void {
    this.utilService.redirecionar('/mentores/busca', { tags: idTag, pagina: 1, totalPorPagina: 6 });
  }

  deslogar(): void {
    this.mensagemService.confirmacaoAlerta({ titulo: 'Deseja sair da conta?' }, () => {
      sessionStorage.clear();
      this.utilService.redirecionar('entrar');
    });
  }

  get usuario() {
    return this.cookiesService.usuario;
  }

}

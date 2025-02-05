import { Component, OnInit } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
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
  tags: Paginacao<Tag> = {
    lista: [],
    pagina: 1,
    totalPaginas: 0,
    totalRegistros: 0
  };

  constructor(
    private cookiesService: CookiesService,
    private tagService: TagService,
    private utilService: UtilService
  ) { }

  ngOnInit(): void {
    const params = { pagina: 1, totalPorPagina: 10 };
    this.tagService.getWithParams(params, this.carregar).subscribe(_ => this.tags = _);
  }

  irPara(rota: string): void {
    this.utilService.redirecionar(rota);
  }

  get usuario() {
    return this.cookiesService.usuario;
  }

}

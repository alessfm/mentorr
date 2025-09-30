import { Component } from '@angular/core';

import { DOMService } from '@core/services/dom.service';

import { EnumTipoTag } from '@shared/enums/tipo-tag.enum';
import { Loading } from '@core/models/loading.model';
import { Tag } from '@shared/models/tag.model';

@Component({
  selector: 'app-lista-tags',
  templateUrl: './lista-tags.component.html',
  styleUrls: ['./lista-tags.component.scss']
})
export class ListaTagsComponent {

  carregar = new Loading();
  enumTipoTag = EnumTipoTag;
  tags: Tag[] = [
    { id: 5, nome: 'agile' },
    { id: 3, nome: 'back-end' },
    { id: 15, nome: 'big data' },
    { id: 6, nome: 'cloud' },
    { id: 4, nome: 'devops' },
    { id: 11, nome: 'figma' },
    { id: 2, nome: 'front-end' },
    { id: 17, nome: 'gestão' },
    { id: 7, nome: 'IA' },
    { id: 10, nome: 'laravel' }
  ];

  constructor(private domService: DOMService) { }

  buscarMentoresPorTag(id: number): void {
    this.domService.redirecionar('/mentores/busca', { tags: id, pagina: 1, totalPorPagina: 6 });
  }

}

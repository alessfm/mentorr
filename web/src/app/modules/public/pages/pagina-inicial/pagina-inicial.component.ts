import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { forkJoin } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PublicMentoresService } from '../../services/public-mentores.service';
import { PublicTagsService } from '@shared/services/public-tags.service';
import { DOMService } from '@core/services/dom.service';

import { Loading } from '@core/models/loading.model';
import { MentorBusca } from '../../models/mentor-public.model';
import { Tag } from '@shared/models/tag.model';
import { TotaisMentores } from '../../models/totais-mentores.model';
import { Total } from '@shared/models/total.model';

@Component({
  selector: 'app-pagina-inicial',
  templateUrl: './pagina-inicial.component.html',
  styleUrls: ['./pagina-inicial.component.scss']
})
export class PaginaInicialComponent implements OnInit {

  carregar = new Loading(true);
  form: FormGroup;

  tags: Tag[] = [];
  totais: Total[] = [];
  mentores: MentorBusca[] = [];
  avaliacoes = [
    {
      nota: 5,
      nomeUsuario: 'Alessandro Figueiredo Malheiro',
      cargoUsuario: 'Desenvolvedor Web Júnior',
      fotoUsuario: 'https://avatars.githubusercontent.com/u/66129133?v=4',
      comentario: 'Codificar o Mentorr é uma baita experiência.\
      A base de muitas idas e vindas ao Stack Overflow, tira-dúvidas com professores, compilações sem fim e\
      bugs por todo lado. A cada dia, sinto-me muito mais confiante em terminar essa jornada, ou, pelo menos, um MVP 😎'
    }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private publicMentoresService: PublicMentoresService,
    private publicTagsService: PublicTagsService,
    private domService: DOMService
  ) {
    this.form = this.formBuilder.group({
      texto: [null],
      pagina: [1],
      totalPorPagina: [6]
    })
  }

  ngOnInit(): void {
    this.buscarDadosTela();
  }

  buscarMentores(): void {
    this.domService.redirecionar('/mentores/busca', this.form.value);
  }

  buscarMentoresPorTag(idTag: number): void {
    this.domService.redirecionar('/mentores/busca', { tags: idTag, pagina: 1, totalPorPagina: 6 });
  }

  verMentor(apelido: string): void {
    this.domService.redirecionar(`/mentores/${apelido}`);
  }

  private buscarDadosTela(): void {
    const $tags = this.publicTagsService.buscarDestaques();
    const $totais = this.publicMentoresService.buscarTotais();
    const $mentores = this.publicMentoresService.buscarDestaques();

    forkJoin([$tags, $totais, $mentores])
      .pipe(finalize(() => this.carregar.load = false))
      .subscribe(([tags, totais, mentores]) => {
        this.tags = tags;
        this.montarTotais(totais);
        this.mentores = mentores;
      })
  }

  private montarTotais(totais: TotaisMentores): void {
    this.totais = [
      {
        valor: totais.qtdMentores,
        descricao: 'Mentores Disponíveis',
        icone: 'fa-chalkboard-user'
      },
      {
        valor: totais.qtdMentorias,
        descricao: 'Parcerias Firmadas',
        icone: 'fa-handshake-angle'
      },
      {
        valor: totais.qtdPaises,
        descricao: 'Países Representados',
        icone: 'fa-earth-americas'
      }
    ];
  }
}

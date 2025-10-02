import { ActivatedRoute } from '@angular/router';
import { Component, ViewChild } from '@angular/core';

import { PublicMentoresService } from '../../services/public-mentores.service';
import { DOMService } from '@core/services/dom.service';

import { ModalSolicitacaoComponent } from '../../components/modal-solicitacao/modal-solicitacao.component';

import { Loading } from '@core/models/loading.model';
import { Avaliacao, Estrela, MentorBusca, MentorPublic, Plano } from '../../models/mentor-public.model';

import { EnumDias } from '@shared/enums/dias.enum';

@Component({
  selector: 'app-perfil-mentor',
  templateUrl: './perfil-mentor.component.html',
  styleUrls: ['./perfil-mentor.component.scss']
})
export class PerfilMentorComponent {

  @ViewChild('modal') modal!: ModalSolicitacaoComponent;

  carregar = new Loading(true);
  mentor!: MentorPublic;
  apelido = '';

  enumDias = EnumDias;
  tipoOrdemAvaliacoes = 'NOTA';
  mostrarDrop = false;

  resumo: any[] = [];
  similares: MentorBusca[] = [];

  constructor(
    private publicMentoresService: PublicMentoresService,
    private route: ActivatedRoute,
    private domService: DOMService
  ) {
    this.route.params.subscribe(params => {
      this.apelido = params.apelido;
      this.obterMentor();
    });
  }

  private obterMentor(): void {
    this.publicMentoresService.buscarPorApelido(this.apelido, this.carregar).subscribe({
      next: _ => this.mentor = _,
      error: () => this.domService.redirecionar('404'),
      complete: () => this.montarTela()
    });
  }

  private montarTela(): void {
    this.gerarResumo();
    this.tratarAvaliacoes();
    this.buscarMentoresSimilares();
  }

  private gerarResumo(): void {
    this.resumo = [];

    const diaAtual = (new Date().getDay() - 1);
    const nomeDia = this.enumDias.find(d => d.codigo == diaAtual)!.valor;
    const horarioAtivo = this.mentor.horarios.find(h => h.dia == nomeDia);

    this.resumo.push(
      {
        icone: 'fa-earth-americas',
        descricao: 'Brasil'
      },
      {
        icone: 'fa-comment-dots',
        descricao: 'Fala português e inglês'
      }
    );

    if (horarioAtivo) {
      this.resumo.push({
        icone: 'fa-clock-rotate-left',
        descricao: 'Ativo hoje!'
      });
    }
  }

  private tratarAvaliacoes(): void {
    this.mentor.avaliacoes.forEach(a => this.montarEstrelas(a));
  }

  private montarEstrelas(avaliacao: Avaliacao): void {
    const nota = avaliacao.nota;
    const estrelas: Estrela[] = avaliacao.estrelas = [];

    if (nota == null) {
      return;
    }

    for (let i = 0.5; i <= nota; i = i + 0.5) {
      const index = estrelas.findIndex(e => e.completa == false);
      (index != -1) ? estrelas[index].completa = true : estrelas.push({ completa: false });
    }
  }

  private buscarMentoresSimilares(): void {
    const params = {
      cargo: this.mentor.cargo,
      empresa: this.mentor.empresa
    };
    this.publicMentoresService.buscarSimilares(this.mentor.apelido, params).subscribe(_ => this.similares = _);
  }

  verMentor(apelido: string): void {
    this.domService.redirecionar(`/mentores/${apelido}`);
  }

  ordenarAvaliacoes(tipo: string): void {
    this.mostrarDrop = false;
    this.tipoOrdemAvaliacoes = tipo;
    switch (tipo) {
      case 'DATA':
        this.mentor.avaliacoes.sort((a, b) => a.data < b.data ? -1 : 1);
        break;
      case 'NOTA':
        this.mentor.avaliacoes.sort((a, b) => a.nota > b.nota ? -1 : 1);
        break;
    }
  }

  abrirModal(plano: Plano): void {
    this.modal.abrirModal(plano);
  }

}

import { ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentorPublicService } from '../../services/mentor-public.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';
import { Avaliacao, Estrela, MentorBusca, MentorPublic } from '../../models/mentor-public.model';
import { Paginacao } from '@shared/models/paginacao.model';
import { Usuario } from '@shared/models/usuario.model';

import { EnumDias } from '@shared/enums/dias.enum';

@Component({
  selector: 'app-perfil-mentor',
  templateUrl: './perfil-mentor.component.html',
  styleUrls: ['./perfil-mentor.component.scss']
})
export class PerfilMentorComponent {

  carregar = new Loading();
  mentor!: MentorPublic;
  private apelido = '';

  enumDias = EnumDias;

  resumo: any[] = [];
  similares: Paginacao<MentorBusca> = {
    lista: [],
    pagina: 1,
    totalPaginas: 0,
    totalRegistros: 0
  };

  constructor(
    private cookiesService: CookiesService,
    private mensagemService: MensagemService,
    private mentorPublicService: MentorPublicService,
    private route: ActivatedRoute,
    private utilService: UtilService
  ) {
    this.route.params.subscribe(params => {
      this.apelido = params.apelido;
      this.obterMentor();
    });
  }

  private obterMentor(): void {
    this.mentorPublicService.buscarPorApelido(this.apelido, this.carregar).subscribe({
      next: _ => this.mentor = _,
      error: () => this.redirecionar(),
      complete: () => !this.mentor.ativo ? this.redirecionar() : this.montarTela()
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
    const nomeDia = this.enumDias.find(d => d.codigo == diaAtual)!.label;
    const horarioAtivo = this.mentor.horarios.find(h => h.dia == nomeDia);

    this.resumo.push(
      {
        icone: 'fa-earth-americas',
        descricao: 'Brasil'
      },
      {
        icone: 'fa-star',
        descricao: 'Novo Mentor!'
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
    const params = { tags: this.mentor.tags.map(t => t.id) };
    this.mentorPublicService.getWithParams(params).subscribe(busca => {
      busca.lista = busca.lista.filter(m => m.apelido != this.apelido);
      this.similares = busca;
    });
  }

  verMentor(apelido: string): void {
    this.utilService.redirecionar(`/mentores/${apelido}`);
  }

  private redirecionar(): void {
    this.carregar.load = true;
    this.visitaMentor ? this.redirecionarMentor() : this.redirecionarUsuario();
  }

  private redirecionarMentor(): void {
    this.mensagemService.notificarAlerta('Cadastro incompleto', 'Finalize as etapas restantes...');
    this.utilService.redirecionar('/mentor/cadastro');
  }

  private redirecionarUsuario(): void {
    this.mensagemService.popupAlerta('', 'Mentor não encontrado ou inativo');
    setTimeout(() => this.utilService.voltar(), 2000);
  }

  get usuario(): Usuario | null {
    return this.cookiesService.usuario;
  }

  get profile(): string | null {
    return this.cookiesService.profile;
  }

  get visitaMentor(): boolean {
    return this.profile == 'MENTOR' && this.apelido == this.usuario?.apelido;
  }
}

import { ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentorPublicService } from '../../services/mentor-public.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';
import { MentorBusca, MentorPublic } from '../../models/mentor-public.model';
import { Paginacao } from '@shared/models/paginacao.model';
import { Usuario } from '@shared/models/usuario.model';

@Component({
  selector: 'app-perfil-mentor',
  templateUrl: './perfil-mentor.component.html',
  styleUrls: ['./perfil-mentor.component.scss']
})
export class PerfilMentorComponent {

  carregar = new Loading();
  mentor!: MentorPublic;
  private apelido = '';

  resumo: any[] = [];
  avaliacoes: any[] = [];
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
    this.buscarAvaliacoes();
    this.buscarMentoresSimilares();
  }

  private gerarResumo(): void {
    this.resumo = [
      {
        icone: 'fa-earth-americas',
        descricao: 'Brasil'
      },
      {
        icone: 'fa-clock-rotate-left',
        descricao: 'Ativo hoje'
      },
      {
        icone: 'fa-star',
        descricao: 'Novo Mentor!'
      }
    ];
  }

  private buscarAvaliacoes(): void {
    this.avaliacoes = [
      {
        nota: 5,
        nomeUsuario: 'Alessandro Figueiredo Malheiro',
        cargoUsuario: 'Desenvolvedor Web Júnior',
        fotoUsuario: 'https://avatars.githubusercontent.com/u/66129133?v=4',
        comentario: 'Codificar o Mentorr é uma baita experiência.\
        A base de muitas idas e vindas ao Stack Overflow, tira-dúvidas com professores, compilações sem fim e\
        bugs por todo lado. A cada dia, sinto-me muito mais confiante em terminar essa jornada, ou, pelo menos, um MVP 😎'
      },
      {
        nota: 4.5,
        nomeUsuario: 'Aluno Genérico',
        cargoUsuario: 'Cargo Básico',
        fotoUsuario: 'https://placehold.co/400',
        comentario: 'Codificar o Mentorr é uma baita experiência.\
        A base de muitas idas e vindas ao Stack Overflow, tira-dúvidas com professores'
      }
    ];
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

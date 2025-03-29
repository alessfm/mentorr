import { Component, OnInit } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
import { GerenciarCadastroService } from '../../../services/gerenciar-cadastro.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentorService } from '../../../services/mentor.service';
import { UtilService } from '@core/services/util.service';

import { Usuario } from '@shared/models/usuario.model';
import { Mentor } from '../../../models/mentor.model';

interface Etapa {
  id: string;
  rota: string;
  titulo: string;
  descricao: string;
  ativa?: boolean;
  concluida?: boolean;
}

@Component({
  selector: 'app-etapas-cadastro',
  templateUrl: './etapas-cadastro.component.html',
  styleUrls: ['./etapas-cadastro.component.scss']
})
export class EtapasCadastroComponent implements OnInit {

  etapas: Etapa[] = [
    {
      id: 'INICIAL',
      rota: 'dados',
      titulo: 'Conte-nos mais sobre você',
      descricao: 'Informe o seu cargo atual e habilidades.\
      Redija a sua bio e anexe uma foto que aparecerá no seu perfil.',
    },
    {
      id: 'PLANOS',
      rota: 'planos',
      titulo: 'Crie planos de mentoria',
      descricao: 'Estipule valores, limites, condições e vagas para cada um.'
    },
    {
      id: 'HORARIOS',
      rota: 'horarios',
      titulo: 'Selecione horários disponíveis',
      descricao: 'Indique os dias e horários que você estará disponível.'
    }
  ];

  constructor(
    private cookiesService: CookiesService,
    private gerenciarService: GerenciarCadastroService,
    private mensagemService: MensagemService,
    private mentorService: MentorService,
    private utilService: UtilService
  ) { }

  ngOnInit(): void {
    const indexAtual = this.etapas.findIndex(e => e.id == this.etapa);

    this.etapas.map((e, index) => {
      e.ativa = (index == indexAtual);
      e.concluida = (index < indexAtual || indexAtual == -1)
    });
  }

  acessar(etapa: Etapa): void {
    if (etapa.ativa) {
      this.utilService.redirecionar(`/mentor/cadastro/${etapa.rota}`);
    }
  }

  concluir(): void {
    !this.mentor.ativo ? this.ativarMentor() : this.redirecionar();
  }

  private ativarMentor(): void {
    this.mentorService.alterarStatus(this.gerenciarService.carregar).subscribe(() => {
      this.mensagemService.popupInfo(
        `Bem-vindo(a) a sua página, ${this.usuario.nome}!`,
        'Se desejar fazer alguma alteração, acesse "Minha Conta" pelo menu superior'
      );
      this.redirecionar();
    })
  }

  private redirecionar(): void {
    this.utilService.redirecionar(`/mentores/${this.usuario.apelido}`);
  }

  get etapa(): string {
    return this.gerenciarService.etapa;
  }

  get mentor(): Mentor {
    return this.gerenciarService.mentor;
  }

  get usuario(): Usuario {
    const usuario = this.cookiesService.usuario!;
    return { ...usuario, nome: usuario.nome.split(' ')[0] };
  }

}

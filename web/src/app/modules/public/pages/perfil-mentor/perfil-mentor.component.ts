import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentorPublicService } from '../../services/mentor-public.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';
import { MentorPublic } from '../../models/mentor-public.model';
import { Usuario } from '@shared/models/usuario.model';

@Component({
  selector: 'app-perfil-mentor',
  templateUrl: './perfil-mentor.component.html',
  styleUrls: ['./perfil-mentor.component.scss']
})
export class PerfilMentorComponent implements OnInit {

  carregar = new Loading();
  mentor!: MentorPublic;
  private apelido = '';

  constructor(
    private cookiesService: CookiesService,
    private mensagemService: MensagemService,
    private mentorPublicService: MentorPublicService,
    private route: ActivatedRoute,
    private utilService: UtilService
  ) {
    this.apelido = this.route.snapshot.params.apelido;
  }

  ngOnInit(): void {
    this.mentorPublicService.buscarPorApelido(this.apelido, this.carregar).subscribe({
      next: _ => this.mentor = _,
      error: () => this.redirecionar(),
      complete: () => {
        if (!this.mentor.ativo) {
          this.redirecionar();
        }
      }
    });
  }

  private redirecionar(): void {
    this.visitaMentor ? this.redirecionarMentor() : this.redirecionarUsuario();
  }

  private redirecionarMentor(): void {
    this.mensagemService.notificarAlerta('Cadastro incompleto', 'Finalize as etapas restantes...');
    this.utilService.redirecionar('/mentor/cadastro');
  }

  private redirecionarUsuario(): void {
    this.mensagemService.popupAlerta('', 'Mentor não encontrado ou inativo');
    setTimeout(() => this.utilService.voltar(), 2000)
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

import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { CookiesService } from '@core/services/cookies.service';
import { MentoriasAlunoService } from '@shared/services/mentorias-aluno.service';
import { MentoriasMentorService } from '@shared/services/mentorias-mentor.service';
import { MensagensMentoriaService } from '@shared/services/mensagens-mentoria.service';

import { ModalTrocaStatusComponent } from './components/modal-troca-status/modal-troca-status.component';

import { Loading } from '@core/models/loading.model';
import { Mentoria } from '@shared/models/mentoria.model';

@Component({
  selector: 'app-gestao-mentorias',
  templateUrl: './gestao-mentorias.component.html',
  styleUrls: ['./gestao-mentorias.component.scss']
})
export class GestaoMentoriasComponent implements OnInit {

  @ViewChild('modal') modal!: ModalTrocaStatusComponent;

  @Input() lista = 'ALUNOS';

  carregar = new Loading();
  mentorias: Mentoria[] = [];

  mentoriaAberta?: Mentoria;
  form: FormGroup;
  mostrarDrop = false;

  constructor(
    private cookiesService: CookiesService,
    private formBuilder: FormBuilder,
    private mentoriasAlunoService: MentoriasAlunoService,
    private mentoriasMentorService: MentoriasMentorService,
    private mensagensMentoriaService: MensagensMentoriaService
  ) {
    this.form = this.formBuilder.group({
      corpo: [null, [Validators.required, Validators.maxLength(1000)]]
    })
  }

  ngOnInit(): void {
    this.buscarMentorias();
  }

  private buscarMentorias(): void {
    this.mentoriaService.getList(this.carregar).subscribe(_ => this.mentorias = _);
  }

  buscarDadosMentoria(mentoria: Mentoria): void {
    this.mentoriaAberta = mentoria;
    this.buscarMensagens();
  }

  private buscarMensagens(): void {
    this.form.reset();
    this.mensagensMentoriaService.buscarTodas(this.idMentoria!, this.carregar).subscribe(_ => this.mentoriaAberta!.mensagens = _);
  }

  responder(): void {
    this.form.patchValue({ corpo: this.form.value.corpo.trim() });
    this.mensagensMentoriaService.enviar(this.idMentoria!, this.form.value, this.carregar).subscribe(() => this.buscarMensagens());
  }

  abrirModal(status: string): void {
    this.modal.abrirModal(this.mentoriaAberta!, status);
  }

  /*/ Base /*/

  get idMentoria() {
    return this.mentoriaAberta ? this.mentoriaAberta.id : null;
  }

  get podeDigitar(): boolean {
    return this.mentoriaAberta ? ['ATIVA', 'PAUSADA'].includes(this.mentoriaAberta.status) : false;
  }

  get mentoriaService() {
    switch (this.lista) {
      case 'MENTORES':
        return this.mentoriasAlunoService;
      case 'ALUNOS':
        return this.mentoriasMentorService;
      default:
        return this.mentoriasAlunoService;
    }
  }

  /*/ Core /*/

  get usuario() {
    return this.cookiesService.usuario;
  }

}

import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { CookiesService } from '@core/services/cookies.service';
import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentoriasMentorService } from '@shared/services/mentorias-mentor.service';

import { Loading } from '@core/models/loading.model';
import { Mentoria } from '@shared/models/mentoria.model';

@Component({
  selector: 'app-modal-troca-status',
  templateUrl: './modal-troca-status.component.html',
  styleUrls: ['./modal-troca-status.component.scss']
})
export class ModalTrocaStatusComponent {

  carregar = new Loading();
  form: FormGroup;
  mentoria?: Mentoria | null = null;
  mostrarModal = false;

  constructor(
    private cookiesService: CookiesService,
    private domService: DOMService,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private mensagemService: MensagemService,
    private mentoriasMentorService: MentoriasMentorService
  ) {
    this.form = this.formBuilder.group({
      status: [null, Validators.required],
      corpo: [null, [Validators.required, Validators.maxLength(1000)]]
    })
  }

  abrirModal(mentoria: Mentoria, status: string): void {
    this.mentoria = mentoria;
    this.form.patchValue({ status: status });
    this.mostrarModal = true;
  }

  fecharModal(): void {
    this.mostrarModal = false;
    this.mentoria = null;
    this.form.reset();
  }

  enviar() {
    this.formService.validarFormEChamarFuncao(this.form);
    this.mentoriasMentorService.alterarStatus(this.mentoria!.id, this.form.value, this.carregar).subscribe(() => {
      this.mensagemService.popupSucesso('Mentoria atualizada!', 'Recarregando...');
      setTimeout(() => this.domService.recarregar(), 500);
    });
  }

  get usuario() {
    const usuario = this.cookiesService.usuario;
    return usuario ? { ...usuario, nome: usuario.nome.split(' ')[0] } : null;
  }

}

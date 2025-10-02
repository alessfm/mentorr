import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { NgxLoadingXConfig, SPINNER, POSITION, NgxLoadingXModule } from 'ngx-loading-x';
import { BotoesModule } from '@shared/components/botoes/botoes.module';
import { FormModule } from '@shared/components/form/form.module';

import { MentoriasAlunoService } from '@shared/services/mentorias-aluno.service';
import { MentoriasMentorService } from '@shared/services/mentorias-mentor.service';
import { MensagensMentoriaService } from '@shared/services/mensagens-mentoria.service';

import { GestaoMentoriasComponent } from './gestao-mentorias.component';
import { ModalTrocaStatusComponent } from './components/modal-troca-status/modal-troca-status.component';

const spinnerConfig: NgxLoadingXConfig = {
  show: false,
  bgBlur: 7,
  bgOpacity: 0.7,
  bgColor: '#fff',
  spinnerSize: 180,
  spinnerColor: '#026df9',
  spinnerType: SPINNER.wanderingCubes,
  spinnerPosition: POSITION.centerCenter
}

@NgModule({
  imports: [
    BotoesModule,
    CommonModule,
    FormModule,
    ReactiveFormsModule,
    NgxLoadingXModule.forRoot(spinnerConfig),
  ],
  declarations: [
    GestaoMentoriasComponent,
    ModalTrocaStatusComponent
  ],
  providers: [
    MentoriasAlunoService,
    MentoriasMentorService,
    MensagensMentoriaService
  ],
  exports: [GestaoMentoriasComponent]
})
export class GestaoMentoriasModule { }

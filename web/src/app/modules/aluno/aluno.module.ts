import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { NgSelectModule } from '@ng-select/ng-select';
import { NgxMaskModule, IConfig } from 'ngx-mask';
import { NgxLoadingXConfig, SPINNER, POSITION, NgxLoadingXModule } from 'ngx-loading-x';
import { NgxPaginationModule } from 'ngx-pagination';

import { BotoesModule } from '@shared/components/botoes/botoes.module';
import { FooterModule } from '@shared/components/footer/footer.module';
import { FormModule } from '@shared/components/form/form.module';
import { GestaoMentoriasModule } from '@shared/modules/gestao-mentorias/gestao-mentorias.module';
import { HeaderModule } from '@shared/components/header/header.module';

import { AlunoRoutingModule } from './aluno.routing';
import { MentoriasAlunoComponent } from './pages/mentorias/mentorias-aluno.component';

const maskConfig: Partial<null | IConfig> | (() => Partial<IConfig>) = {
  validation: false,
  dropSpecialCharacters: true,
}

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
    FormsModule,
    FooterModule,
    GestaoMentoriasModule,
    HeaderModule,
    NgSelectModule,
    NgxMaskModule.forRoot(maskConfig),
    NgxLoadingXModule.forRoot(spinnerConfig),
    NgxPaginationModule,
    AlunoRoutingModule,
    ReactiveFormsModule,
    RouterModule
  ],
  declarations: [
    MentoriasAlunoComponent
  ],
  providers: []
})
export class AlunoModule { }

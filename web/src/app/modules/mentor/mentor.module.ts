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
import { HeaderModule } from '@shared/components/header/header.module';

import { MentorService } from './services/mentor.service';
import { GerenciarCadastroService } from './services/gerenciar-cadastro.service';
import { HorariosService } from './services/horarios.service';
import { PlanosService } from './services/planos.service';
import { TagService } from '@shared/services/tag.service';

import { MentorRoutingModule } from './mentor.routing';
import { CadastroMentorComponent } from './pages/cadastro-mentor/cadastro-mentor.component';
import {
  EtapasCadastroComponent,
  CadastroDadosComponent,
  CadastroPlanosComponent,
  CadastroHorariosComponent
} from './components/cadastro-mentor';

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
    HeaderModule,
    NgSelectModule,
    NgxMaskModule.forRoot(maskConfig),
    NgxLoadingXModule.forRoot(spinnerConfig),
    NgxPaginationModule,
    MentorRoutingModule,
    ReactiveFormsModule,
    RouterModule
  ],
  declarations: [
    // Cadastro do Mentor
    CadastroMentorComponent,
    EtapasCadastroComponent,
    CadastroDadosComponent,
    CadastroPlanosComponent,
    CadastroHorariosComponent,
  ],
  providers: [
    MentorService,
    GerenciarCadastroService,
    HorariosService,
    PlanosService,
    TagService
  ]
})
export class MentorModule { }

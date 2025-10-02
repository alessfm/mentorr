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

import { GerenciarCadastroService } from './services/gerenciar-cadastro.service';
import { HorariosMentorService } from './services/horarios-mentor.service';
import { MentorService } from './services/mentor.service';
import { PlanosMentorService } from './services/planos-mentor.service';
import { PublicTagsService } from '@shared/services/public-tags.service';
import { TagsMentorService } from './services/tags-mentor.service';

import { MentorRoutingModule } from './mentor.routing';
import { CadastroMentorComponent } from './pages/cadastro-mentor/cadastro-mentor.component';
import * as CADASTRO from './components/cadastro-mentor';
import { MentoriasMentorComponent } from './pages/mentorias-mentor/mentorias-mentor.component';

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
    MentorRoutingModule,
    ReactiveFormsModule,
    RouterModule
  ],
  declarations: [
    // Cadastro do Mentor
    CadastroMentorComponent,
    CADASTRO.EtapasCadastroComponent,
    CADASTRO.CadastroDadosComponent,
    CADASTRO.CadastroPlanosComponent,
    CADASTRO.CadastroHorariosComponent,

    // Gestão de Mentorias
    MentoriasMentorComponent
  ],
  providers: [
    GerenciarCadastroService,
    HorariosMentorService,
    MentorService,
    PlanosMentorService,
    PublicTagsService,
    TagsMentorService
  ]
})
export class MentorModule { }

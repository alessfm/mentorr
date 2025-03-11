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
import { HeaderModule } from '@shared/components/header/header.module';

import { MentorService } from './services/mentor.service';
import { TagService } from '@shared/services/tag.service';

import { MentorRoutingModule } from './mentor.routing';
import { CadastroMentorComponent } from './pages/cadastro-mentor/cadastro-mentor.component';

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
    CadastroMentorComponent
  ],
  providers: [
    MentorService,
    TagService
  ]
})
export class MentorModule { }

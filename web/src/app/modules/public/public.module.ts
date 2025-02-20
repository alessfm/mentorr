import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { NgSelectModule } from '@ng-select/ng-select';
import { NgxMaskModule, IConfig } from 'ngx-mask';
import { NgxLoadingXConfig, SPINNER, POSITION, NgxLoadingXModule } from 'ngx-loading-x';
import { NgxPaginationModule } from 'ngx-pagination';

import { BotoesModule } from '@shared/components/botoes/botoes.module';
import { FooterModule } from '@shared/components/footer/footer.module';
import { HeaderModule } from '@shared/components/header/header.module';

import { AuthService } from '@shared/services/auth.service';
import { UsuarioService } from '@shared/services/usuario.service';
import { MentorPublicService } from './services/mentor-public.service';
import { TagService } from '@shared/services/tag.service';

import { PublicRoutingModule } from './public.routing';
import { CardMentorComponent } from './components/card-mentor/card-mentor.component';
import { Erro404Component } from './pages/erro-404/erro-404.component';
import { ListaMentoresComponent } from './pages/lista-mentores/lista-mentores.component';
import { LoginComponent } from './pages/login/login.component';
import { PaginaInicialComponent } from './pages/pagina-inicial/pagina-inicial.component';
import { PerfilMentorComponent } from './pages/perfil-mentor/perfil-mentor.component';
import { SecaoPropagandaComponent } from './components/propaganda/secao-propaganda.component';

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
    PublicRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    CardMentorComponent,
    Erro404Component,
    ListaMentoresComponent,
    LoginComponent,
    PaginaInicialComponent,
    PerfilMentorComponent,
    SecaoPropagandaComponent
  ],
  providers: [
    AuthService,
    MentorPublicService,
    TagService,
    UsuarioService
  ]
})
export class PublicModule { }

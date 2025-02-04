import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { NgxLoadingXConfig, SPINNER, POSITION, NgxLoadingXModule } from 'ngx-loading-x';

import { FooterModule } from '@shared/components/footer/footer.module';
import { HeaderModule } from '@shared/components/header/header.module';

import { AuthService } from '@shared/services/auth.service';
import { PublicService } from './services/public.service';

import { PublicRoutingModule } from './public.routing';
import { ApresentacaoComponent } from './pages/apresentacao/apresentacao.component';
// import { Erro404Component } from './pages/erro-404/erro-404.component';
// import { Erro500Component } from './pages/erro-500/erro-500.component';
import { LoginComponent } from './pages/login/login.component';

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
    CommonModule,
    FormsModule,
    FooterModule,
    HeaderModule,
    NgxLoadingXModule.forRoot(spinnerConfig),
    PublicRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    ApresentacaoComponent,
    // Erro404Component,
    // Erro500Component,
    LoginComponent
  ],
  providers: [
    AuthService,
    PublicService
  ]
})
export class PublicModule { }

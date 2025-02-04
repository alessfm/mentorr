import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ApresentacaoComponent } from './pages/apresentacao/apresentacao.component';
import { Erro404Component } from './pages/erro-404/erro-404.component';
import { Erro500Component } from './pages/erro-500/erro-500.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path: '', component: ApresentacaoComponent
  },
  {
    path: '404', component: Erro404Component
  },
  {
    path: '500', component: Erro500Component
  },
  {
    path: 'entrar', component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ApresentacaoComponent } from './pages/apresentacao/apresentacao.component';
import { Erro404Component } from './pages/erro-404/erro-404.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path: '', component: ApresentacaoComponent
  },
  {
    path: '404', component: Erro404Component
  },
  {
    path: 'login', component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }

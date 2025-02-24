import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Erro404Component } from './pages/erro-404/erro-404.component';
import { ListaMentoresComponent } from './pages/lista-mentores/lista-mentores.component';
import { LoginComponent } from './pages/login/login.component';
import { PaginaInicialComponent } from './pages/pagina-inicial/pagina-inicial.component';
import { PerfilMentorComponent } from './pages/perfil-mentor/perfil-mentor.component';

const routes: Routes = [
  {
    path: '', component: PaginaInicialComponent
  },
  {
    path: '404', component: Erro404Component
  },
  {
    path: 'entrar', component: LoginComponent
  },
  {
    path: 'mentores/busca', component: ListaMentoresComponent
  },
  {
    path: 'mentores/:apelido', component: PerfilMentorComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }

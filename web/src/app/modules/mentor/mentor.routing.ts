import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CadastroMentorComponent } from './pages/cadastro-mentor/cadastro-mentor.component';
import {
  EtapasCadastroComponent,
  CadastroDadosComponent,
  CadastroPlanosComponent,
  CadastroHorariosComponent
} from './components/cadastro-mentor';

const routes: Routes = [
  {
    path: 'cadastro',
    component: CadastroMentorComponent,
    children: [
      {
        path: '',
        redirectTo: 'etapas',
        pathMatch: 'full',
      },
      {
        path: 'etapas',
        component: EtapasCadastroComponent
      },
      {
        path: 'dados',
        component: CadastroDadosComponent
      },
      {
        path: 'planos',
        component: CadastroPlanosComponent
      },
      {
        path: 'horarios',
        component: CadastroHorariosComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MentorRoutingModule { }

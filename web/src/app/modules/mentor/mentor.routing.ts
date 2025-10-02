import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CadastroMentorComponent } from './pages/cadastro-mentor/cadastro-mentor.component';
import * as CADASTRO from './components/cadastro-mentor';
import { MentoriasMentorComponent } from './pages/mentorias-mentor/mentorias-mentor.component';

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
        component: CADASTRO.EtapasCadastroComponent
      },
      {
        path: 'dados',
        component: CADASTRO.CadastroDadosComponent
      },
      {
        path: 'planos',
        component: CADASTRO.CadastroPlanosComponent
      },
      {
        path: 'horarios',
        component: CADASTRO.CadastroHorariosComponent
      }
    ]
  },
  {
    path: 'mentorias',
    component: MentoriasMentorComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MentorRoutingModule { }

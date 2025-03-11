import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CadastroMentorComponent } from './pages/cadastro-mentor/cadastro-mentor.component';

const routes: Routes = [
  {
    path: 'cadastro', component: CadastroMentorComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MentorRoutingModule { }

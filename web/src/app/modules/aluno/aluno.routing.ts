import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MentoriasAlunoComponent } from './pages/mentorias/mentorias-aluno.component';

const routes: Routes = [
  {
    path: 'mentorias',
    component: MentoriasAlunoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlunoRoutingModule { }

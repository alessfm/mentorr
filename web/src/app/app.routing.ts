import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LogadoGuard } from '@core/guards/logado.guard';
import { RoleGuard } from '@core/guards/role.guard';
import { PublicModule } from '@modules/public/public.module';

const routes: Routes = [
  {
    path: 'mentor',
    canActivate: [LogadoGuard, RoleGuard],
    loadChildren: () => import('@app/modules/mentor/mentor.module').then(m => m.MentorModule),
    data: { profile: 'MENTOR' }
  },
  {
    path: '**', redirectTo: '404'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    PublicModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

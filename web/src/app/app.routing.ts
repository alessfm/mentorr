import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// import { LogadoGuard } from '@app/core/guards/logado.guard';
// import { PublicModule } from '@modules/public/public.module';

const routes: Routes = [
  // {
  //   path: 'perfil',
  //   canActivate: [LogadoGuard],
  //   loadChildren: () => import('@modules/perfil/perfil.module').then(m => m.PerfilModule)
  // },
  {
    path: '**', redirectTo: '404'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    // PublicModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

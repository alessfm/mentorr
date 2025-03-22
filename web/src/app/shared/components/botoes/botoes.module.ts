import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BotaoAzulComponent } from './botao-azul.component';
import { BotaoBrancoComponent } from './botao-branco.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    BotaoAzulComponent,
    BotaoBrancoComponent
  ],
  exports: [
    BotaoAzulComponent,
    BotaoBrancoComponent
  ]
})
export class BotoesModule { }

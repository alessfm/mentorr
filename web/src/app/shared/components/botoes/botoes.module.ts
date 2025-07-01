import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BotaoAzulComponent } from './botao-azul.component';
import { BotaoBrancoComponent } from './botao-branco.component';
import { BotaoBordaAzulComponent } from './botao-borda-azul.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    BotaoAzulComponent,
    BotaoBrancoComponent,
    BotaoBordaAzulComponent
  ],
  exports: [
    BotaoAzulComponent,
    BotaoBrancoComponent,
    BotaoBordaAzulComponent
  ]
})
export class BotoesModule { }

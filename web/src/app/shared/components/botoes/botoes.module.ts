import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BotaoAzulComponent } from './botao-azul.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    BotaoAzulComponent,
  ],
  exports: [
    BotaoAzulComponent,
  ]
})
export class BotoesModule { }

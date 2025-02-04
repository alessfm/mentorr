import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header.component';
import { BotoesModule } from '@shared/components/botoes/botoes.module';

@NgModule({
  imports: [
    CommonModule,
    BotoesModule
  ],
  declarations: [
    HeaderComponent
  ],
  exports: [
    HeaderComponent
  ]
})
export class HeaderModule { }

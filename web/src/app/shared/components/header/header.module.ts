import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { BotoesModule } from '@shared/components/botoes/botoes.module';

import { TagService } from '@shared/services/tag.service';

import { HeaderComponent } from './header.component';

@NgModule({
  imports: [
    CommonModule,
    BotoesModule,
    RouterModule
  ],
  declarations: [
    HeaderComponent
  ],
  exports: [
    HeaderComponent
  ],
  providers: [
    TagService
  ]
})
export class HeaderModule { }

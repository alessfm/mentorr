import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { ContarLetrasComponent } from './contar-letras.component';

@NgModule({
  declarations: [ContarLetrasComponent],
  imports: [ReactiveFormsModule],
  exports: [ContarLetrasComponent]
})
export class FormModule { }

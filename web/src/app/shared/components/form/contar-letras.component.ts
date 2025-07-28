import { FormGroup } from '@angular/forms';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-contar-letras',
  template: '<p><span>{{ tamanhoMaximo - form.controls[campo]?.value?.length || 0 }}</span> caracteres restantes.</p>'
})
export class ContarLetrasComponent {

  @Input() campo!: string;
  @Input() form!: FormGroup;
  @Input() tamanhoMaximo = 0;

}

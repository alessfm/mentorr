import { Component, Output, Input, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-botao-azul',
  template: `<button
                (click)="funcao()"
                class="bg-primary text-white font-semibold rounded-lg px-4 py-2 shadow {{estilo}}"
                [disabled]="disabled"
                [type]="type">
                {{ label }}
              </button>`
})
export class BotaoAzulComponent {

  @Output() func = new EventEmitter();
  @Input() label = '';
  @Input() estilo = '';
  @Input() type = 'button';
  @Input() disabled = false;

  funcao() {
    this.func.emit();
  }

}

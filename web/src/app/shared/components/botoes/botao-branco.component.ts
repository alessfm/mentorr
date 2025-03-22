import { Component, Output, Input, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-botao-branco',
  template: `<button
                (click)="funcao()"
                class="bg-slate-200 font-semibold rounded-lg px-4 py-2 shadow {{estilo}}"
                [disabled]="disabled"
                type="button">
                {{ label }}
              </button>`
})
export class BotaoBrancoComponent {

  @Output() func = new EventEmitter();
  @Input() label = '';
  @Input() estilo = '';
  @Input() disabled = false;

  funcao() {
    this.func.emit();
  }

}

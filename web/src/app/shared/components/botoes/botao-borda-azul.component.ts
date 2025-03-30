import { Component, Output, Input, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-botao-borda-azul',
  template: `<button
                (click)="funcao()"
                class="border-2 border-primary text-primary btn {{estilo}}"
                [disabled]="disabled"
                type="button">
                {{ label }}
              </button>`
})
export class BotaoBordaAzulComponent {

  @Output() func = new EventEmitter();
  @Input() label = '';
  @Input() estilo = '';
  @Input() disabled = false;

  funcao() {
    this.func.emit();
  }

}

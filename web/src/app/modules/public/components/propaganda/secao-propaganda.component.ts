import { Component } from '@angular/core';

import { DOMService } from '@core/services/dom.service';

@Component({
  selector: 'app-secao-propaganda',
  templateUrl: './secao-propaganda.component.html',
  styleUrls: ['./secao-propaganda.component.scss']
})
export class SecaoPropagandaComponent {

  constructor(private domService: DOMService) { }

  irParaCadastro(tipo: string): void {
    this.domService.redirecionar(`/cadastro/${tipo}`);
  }
}

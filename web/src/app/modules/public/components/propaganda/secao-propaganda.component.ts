import { Component } from '@angular/core';

import { UtilService } from '@core/services/util.service';

@Component({
  selector: 'app-secao-propaganda',
  templateUrl: './secao-propaganda.component.html',
  styleUrls: ['./secao-propaganda.component.scss']
})
export class SecaoPropagandaComponent {

  constructor(private utilService: UtilService) { }

  irParaCadastro(): void {
    this.utilService.redirecionar('/cadastro');
  }
}

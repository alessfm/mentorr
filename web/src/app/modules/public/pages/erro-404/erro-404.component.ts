import { Component } from '@angular/core';

import { DOMService } from '@core/services/dom.service';

@Component({
  selector: 'app-erro-404',
  templateUrl: './erro-404.component.html',
  styleUrls: ['./erro-404.component.scss']
})
export class Erro404Component {

  constructor(private domService: DOMService) { }

  voltar(): void {
    this.domService.redirecionar('');
  }

}

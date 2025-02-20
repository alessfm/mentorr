import { Component } from '@angular/core';

import { UtilService } from '@core/services/util.service';

@Component({
  selector: 'app-erro-404',
  templateUrl: './erro-404.component.html',
  styleUrls: ['./erro-404.component.scss']
})
export class Erro404Component {

  constructor(private utilService: UtilService) { }

  voltar(): void {
    this.utilService.voltar();
  }

}

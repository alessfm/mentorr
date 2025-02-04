import { Component } from '@angular/core';

import { UtilService } from '@core/services/util.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {

  constructor(
    private utilService: UtilService
  ) { }

  irPara(rota: string): void {
    this.utilService.redirecionar(`/${rota}`);
  }

  get versao() {
    return null;
  }

}

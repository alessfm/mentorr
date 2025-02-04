import { Component } from '@angular/core';

import { NgSelectConfig } from '@ng-select/ng-select';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(
    private ngSelectConfig: NgSelectConfig
  ) {
    this.ngSelectConfig.placeholder = 'Selecione...';
    this.ngSelectConfig.loadingText = 'Carregando...';
    this.ngSelectConfig.notFoundText = 'Nada encontrado...';
  }
}

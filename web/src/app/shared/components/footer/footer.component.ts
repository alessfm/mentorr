import { Component } from '@angular/core';

import { DOMService } from '@core/services/dom.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {

  textos = [
    {
      titulo: 'Plataforma',
      links: [
        {
          descricao: 'Busque Mentores',
          rota: '/mentores/busca'
        },
        {
          descricao: 'Torne-se um mentor',
          rota: '/cadastro/mentor'
        }
      ]
    }
  ];

  constructor(private domService: DOMService) { }

  irPara(rota: string): void {
    this.domService.redirecionar(`/${rota}`);
  }

}

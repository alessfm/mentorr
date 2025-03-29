import { Component, OnDestroy, OnInit } from '@angular/core';

import { GerenciarCadastroService } from '../../services/gerenciar-cadastro.service';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-cadastro-mentor',
  templateUrl: './cadastro-mentor.component.html',
  styleUrls: ['./cadastro-mentor.component.scss']
})
export class CadastroMentorComponent implements OnInit, OnDestroy {

  constructor(private gerenciarService: GerenciarCadastroService) { }

  ngOnInit(): void {
    this.gerenciarService.buscarMentor();
  }

  ngOnDestroy(): void {
    this.gerenciarService.resetarTudo();
  }

  get carregar(): Loading {
    return this.gerenciarService.carregar;
  }

}

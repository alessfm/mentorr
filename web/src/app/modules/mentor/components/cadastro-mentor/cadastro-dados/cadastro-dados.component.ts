import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { GerenciarCadastroService } from '../../../services/gerenciar-cadastro.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentorService } from '../../../services/mentor.service';
import { PublicTagsService } from '@shared/services/public-tags.service';

import { Loading } from '@core/models/loading.model';
import { Paginacao } from '@shared/models/paginacao.model';
import { Tag } from '@shared/models/tag.model';

@Component({
  selector: 'app-cadastro-dados',
  templateUrl: './cadastro-dados.component.html',
  styleUrls: ['./cadastro-dados.component.scss']
})
export class CadastroDadosComponent implements OnInit {

  carregar = new Loading();
  form: FormGroup;
  tab = 1;

  carregarTags = new Loading();
  tags: Paginacao<Tag> = {
    lista: [],
    pagina: 1,
    totalPorPagina: 10,
    totalPaginas: 0,
    totalRegistros: 0
  };

  constructor(
    private formBuilder: FormBuilder,
    private formService: FormService,
    private gerenciarService: GerenciarCadastroService,
    private mensagemService: MensagemService,
    private mentorService: MentorService,
    private publicTagsService: PublicTagsService,
    private domService: DOMService
  ) {
    this.form = this.formBuilder.group({
      foto: [null, Validators.required],
      descricao: [null, Validators.required],
      cargo: [null, Validators.required],
      empresa: [null, Validators.required],
      dataInicio: [null, Validators.required],
      tags: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.buscarTags();
  }

  salvar(): void {
    this.formService.validarFormEChamarFuncao(this.form);
    this.mentorService.save(this.form.value, this.carregar).subscribe(_ => {
      this.mensagemService.popupSucesso('Dados salvos com sucesso!', 'Vamos continuar o seu cadastro...');
      this.gerenciarService.buscarMentor();
      this.domService.redirecionar('/mentor/cadastro');
    });
  }

  buscarTags(): void {
    this.publicTagsService.getWithParams({ pagina: 1, totalPorPagina: 999 }, this.carregarTags).subscribe(_ => this.tags = _);
  }

  get foto(): string {
    return this.form.value.foto;
  }

}

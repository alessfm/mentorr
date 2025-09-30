import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { GerenciarCadastroService } from '../../../services/gerenciar-cadastro.service';
import { MensagemService } from '@core/services/mensagem.service';
import { PlanosMentorService } from '../../../services/planos-mentor.service';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-cadastro-planos',
  templateUrl: './cadastro-planos.component.html'
})
export class CadastroPlanosComponent implements OnInit {

  carregar = new Loading();
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private formService: FormService,
    private gerenciarService: GerenciarCadastroService,
    private mensagemService: MensagemService,
    private planosService: PlanosMentorService,
    private domService: DOMService
  ) {
    this.form = this.formBuilder.group({
      planos: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
    this.criarPlano();
  }

  criarPlano(): void {
    this.carregando();
    this.planos.push(
      this.formBuilder.group({ valor: [null, Validators.required] })
    );
  }

  removerPlano(index: number): void {
    this.carregando();
    this.planos.removeAt(index);
  }

  salvar(): void {
    this.formService.validarFormEChamarFuncao(this.form);
    this.planosService.salvarLote(this.form.value.planos, this.carregar).subscribe(_ => {
      this.mensagemService.popupSucesso('Planos salvos com sucesso!', 'Vamos continuar o seu cadastro...');
      this.gerenciarService.buscarMentor();
      this.domService.redirecionar('/mentor/cadastro');
    });
  }

  private carregando(): void {
    this.carregar.load = true;
    setTimeout(() => this.carregar.load = false, 150);
  }

  get planos() {
    return this.form.get('planos') as FormArray;
  }

}

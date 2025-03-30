import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { GerenciarCadastroService } from '../../../services/gerenciar-cadastro.service';
import { MensagemService } from '@core/services/mensagem.service';
import { PlanosService } from '../../../services/planos.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';
import { Mentor } from '../../../models/mentor.model';

@Component({
  selector: 'app-cadastro-planos',
  templateUrl: './cadastro-planos.component.html'
})
export class CadastroPlanosComponent implements OnInit {

  carregar = new Loading();
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private gerenciarService: GerenciarCadastroService,
    private mensagemService: MensagemService,
    private planosService: PlanosService,
    private utilService: UtilService
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
    this.utilService.verificarForm(this.form);
    if (this.form.invalid) {
      return this.mensagemService.popupFormularioInvalido();
    }

    this.planosService.salvarLote(this.mentor.id, this.form.value.planos, this.carregar).subscribe(_ => {
      this.mensagemService.popupSucesso('Planos salvos com sucesso!', 'Vamos continuar o seu cadastro...');
      this.gerenciarService.buscarMentor();
      this.utilService.redirecionar('/mentor/cadastro');
    });
  }

  private carregando(): void {
    this.carregar.load = true;
    setTimeout(() => this.carregar.load = false, 150);
  }

  get mentor(): Mentor {
    return this.gerenciarService.mentor;
  }

  get planos() {
    return this.form.get('planos') as FormArray;
  }

}

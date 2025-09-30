import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { GerenciarCadastroService } from '../../../services/gerenciar-cadastro.service';
import { MensagemService } from '@core/services/mensagem.service';
import { HorariosMentorService } from '../../../services/horarios-mentor.service';

import { Loading } from '@core/models/loading.model';
import { EnumDias } from '@shared/enums/dias.enum';

@Component({
  selector: 'app-cadastro-horarios',
  templateUrl: './cadastro-horarios.component.html'
})
export class CadastroHorariosComponent implements OnInit {

  carregar = new Loading();
  form: FormGroup;
  enumDias = EnumDias;

  constructor(
    private formBuilder: FormBuilder,
    private formService: FormService,
    private gerenciarService: GerenciarCadastroService,
    private mensagemService: MensagemService,
    private horariosService: HorariosMentorService,
    private domService: DOMService
  ) {
    this.form = this.formBuilder.group({
      horarios: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
    this.enumDias.forEach(d => this.criarHorario(d.codigo));
    this.formService.desabilitarCampos(this.form, Object.keys(this.form.controls));
  }

  criarHorario(dia?: number): void {
    this.carregando();
    this.horarios.push(
      this.formBuilder.group({
        dia: [dia, Validators.required],
        descricaoDia: [this.enumDias.find(d => d.codigo == dia)!.label],
        horaInicio: [null, Validators.required],
        horaFim: [null, Validators.required]
      })
    );
  }

  gerenciarHorario(index: number): void {
    this.carregando();
    const control = this.horarios.at(index);
    control.disabled ? control.enable() : control.disable();
  }

  salvar(): void {
    this.formService.validarFormEChamarFuncao(this.form);
    this.horariosService.salvarLote(this.form.value.horarios, this.carregar).subscribe(_ => {
      this.mensagemService.popupSucesso('Horários salvos com sucesso!', 'Vamos continuar o seu cadastro...');
      this.gerenciarService.buscarMentor();
      this.domService.redirecionar('/mentor/cadastro');
    });
  }

  private carregando(): void {
    this.carregar.load = true;
    setTimeout(() => this.carregar.load = false, 150);
  }

  get horarios() {
    return this.form.get('horarios') as FormArray;
  }

  get horariosAtivos() {
    return this.horarios.controls.filter(h => h.enabled);
  }

}

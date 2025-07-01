import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { GerenciarCadastroService } from '../../../services/gerenciar-cadastro.service';
import { MensagemService } from '@core/services/mensagem.service';
import { HorariosService } from '../../../services/horarios.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';
import { Mentor } from '../../../models/mentor.model';
import { Dias } from '@shared/enums/dias.enum';

@Component({
  selector: 'app-cadastro-horarios',
  templateUrl: './cadastro-horarios.component.html'
})
export class CadastroHorariosComponent implements OnInit {

  carregar = new Loading();
  form: FormGroup;
  enumDias = new Dias().lista;

  constructor(
    private formBuilder: FormBuilder,
    private gerenciarService: GerenciarCadastroService,
    private mensagemService: MensagemService,
    private horariosService: HorariosService,
    private utilService: UtilService
  ) {
    this.form = this.formBuilder.group({
      horarios: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
    this.enumDias.forEach(d => this.criarHorario(d.codigo));
    this.utilService.desabilitarCampos(this.form, Object.keys(this.form.controls));
  }

  criarHorario(dia: number): void {
    this.carregando();
    this.horarios.push(
      this.formBuilder.group({
        dia: [dia, Validators.required],
        descricaoDia: [this.enumDias.find(d => d.codigo == dia)!.nome],
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
    this.utilService.verificarForm(this.form);
    if (this.form.invalid) {
      return this.mensagemService.popupFormularioInvalido();
    }

    this.horariosService.salvarLote(this.mentor.id, this.form.value.horarios, this.carregar).subscribe(_ => {
      this.mensagemService.popupSucesso('Horários salvos com sucesso!', 'Vamos continuar o seu cadastro...');
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

  get horarios() {
    return this.form.get('horarios') as FormArray;
  }

  get horariosAtivos() {
    return this.horarios.controls.filter(h => h.enabled);
  }

}

import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@shared/services/auth.service';
import { CookiesService } from '@core/services/cookies.service';
import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { MensagemService } from '@core/services/mensagem.service';
import { MentoriasAlunoService } from '@shared/services/mentorias-aluno.service';
import { PublicUsuariosService } from '@modules/public/services/public-usuarios.service';
import { UsuarioService } from '@shared/services/usuario.service';

import { Loading } from '@core/models/loading.model';
import { MentorBusca, Plano } from '../../models/mentor-public.model';

@Component({
  selector: 'app-modal-solicitacao',
  templateUrl: './modal-solicitacao.component.html',
  styleUrls: ['./modal-solicitacao.component.scss']
})
export class ModalSolicitacaoComponent {

  @Input() mentor!: MentorBusca;

  carregar = new Loading();
  formEntrar: FormGroup;
  formCadastro: FormGroup;
  formSolicitacao: FormGroup;
  tab = 1;

  verSenha = false;
  mostrarModal = false;

  plano!: Plano;

  constructor(
    private authService: AuthService,
    private cookiesService: CookiesService,
    private domService: DOMService,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private mensagemService: MensagemService,
    private mentoriasAlunoService: MentoriasAlunoService,
    private publicUsuariosService: PublicUsuariosService,
    private usuarioService: UsuarioService
  ) {
    this.formEntrar = this.formBuilder.group({
      apelido: [null, Validators.required],
      senha: [null, Validators.required]
    })

    this.formCadastro = this.formBuilder.group({
      nome: [null, [Validators.required, Validators.maxLength(60)]],
      apelido: [null, [Validators.required, Validators.maxLength(16)]],
      email: [null, [Validators.required, Validators.email]],
      senha: [null, [Validators.required, Validators.pattern('(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}')]]
    })

    this.formSolicitacao = this.formBuilder.group({
      apelidoMentor: [null, Validators.required],
      codigoPlano: [null, Validators.required],
      corpo: [null, [Validators.required, Validators.maxLength(1000)]]
    })
  }

  abrirModal(plano: Plano): void {
    if (this.usuario?.apelido == this.mentor.apelido) {
      this.mensagemService.popupInfo('🤔 Hmm...', 'Que tal escolher outro mentor?');
      return this.fecharModal();
    }

    this.plano = plano;
    this.mostrarModal = true;
  }

  fecharModal(): void {
    this.mostrarModal = false;
    this.formEntrar.reset();
    this.formCadastro.reset();
    this.formSolicitacao.reset();
  }

  criarConta(): void {
    this.formService.validarFormEChamarFuncao(this.formCadastro);
    this.publicUsuariosService.criarConta('aluno', this.formCadastro.value, this.carregar).subscribe(() => {
      this.formEntrar.patchValue({ apelido: this.formCadastro.value.apelido, senha: this.formCadastro.value.senha })
      this.entrar();
    });
  }

  entrar(): void {
    this.formService.validarFormEChamarFuncao(this.formEntrar);
    this.authService.entrarNaConta(this.formEntrar.value, this.carregar).subscribe(_ => {
      this.cookiesService.salvarDados(_);
      this.buscarConta();
    });
  }

  private buscarConta(): void {
    this.usuarioService.get(this.carregar).subscribe(usuario => {
      this.cookiesService.salvarUsuario(usuario);
    });
  }

  solicitar() {
    this.formSolicitacao.patchValue({
      apelidoMentor: this.mentor.apelido,
      codigoPlano: this.plano.codigo
    });

    this.formService.validarFormEChamarFuncao(this.formSolicitacao);
    this.mentoriasAlunoService.solicitar(this.formSolicitacao.value, this.carregar).subscribe(() => {
      this.mensagemService.popupSucesso('Mentoria solicitada!', 'Aguarde a resposta do mentor...');
      this.domService.redirecionar('/aluno/mentorias');
    });
  }

  get usuario() {
    const usuario = this.cookiesService.usuario;
    return usuario ? { ...usuario, nome: usuario.nome.split(' ')[0] } : null;
  }

}

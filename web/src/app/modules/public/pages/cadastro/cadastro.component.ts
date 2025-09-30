import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@shared/services/auth.service';
import { CookiesService } from '@core/services/cookies.service';
import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { MensagemService } from '@core/services/mensagem.service';
import { PublicUsuariosService } from '@modules/public/services/public-usuarios.service';
import { UsuarioService } from '@shared/services/usuario.service';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent {

  carregar = new Loading();
  form: FormGroup;

  constructor(
    private authService: AuthService,
    private cookiesService: CookiesService,
    private domService: DOMService,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private mensagemService: MensagemService,
    private route: ActivatedRoute,
    private publicUsuariosService: PublicUsuariosService,
    private usuarioService: UsuarioService,
  ) {
    this.form = this.formBuilder.group({
      nome: [null, [Validators.required, Validators.maxLength(60)]],
      apelido: [null, [Validators.required, Validators.maxLength(16)]],
      email: [null, [Validators.required, Validators.email]],
      senha: [null, [Validators.required, Validators.pattern('(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}')]]
    })
  }

  criarConta(): void {
    this.formService.validarFormEChamarFuncao(this.form);
    this.publicUsuariosService.criarConta(this.tipoUsuario, this.form.value, this.carregar).subscribe(() => {
      this.mensagemService.notificarSucesso('Conta criada com sucesso!', 'Entrando...');
      this.entrar();
    });
  }

  private entrar(): void {
    this.authService.entrarNaConta(this.form.value, this.carregar).subscribe(_ => {
      this.cookiesService.salvarDados(_);
      this.buscarConta();
    });
  }

  private buscarConta(): void {
    this.usuarioService.get(this.carregar).subscribe(usuario => {
      this.cookiesService.salvarUsuario(usuario);

      switch (usuario.tipo) {
        case 'ALUNO':
          this.domService.redirecionar('/aluno/mentorias');
          break;
        case 'MENTOR':
          this.domService.redirecionar('/mentor/cadastro');
          break;
        default:
          this.domService.redirecionar('/');
      }
    });
  }

  get tipoUsuario(): string {
    return this.route.snapshot.params.tipo;
  }
}

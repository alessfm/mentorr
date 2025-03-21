import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@shared/services/auth.service';
import { CookiesService } from '@core/services/cookies.service';
import { MensagemService } from '@core/services/mensagem.service';
import { UsuarioService } from '@shared/services/usuario.service';
import { UtilService } from '@core/services/util.service';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  carregar = new Loading();
  form: FormGroup;

  constructor(
    private authService: AuthService,
    private cookiesService: CookiesService,
    private formBuilder: FormBuilder,
    private mensagemService: MensagemService,
    private usuarioService: UsuarioService,
    private utilService: UtilService
  ) {
    this.form = this.formBuilder.group({
      apelido: [null, Validators.required],
      senha: [null, Validators.required]
    })
  }

  entrar(): void {
    this.utilService.verificarForm(this.form);
    if (this.form.invalid) {
      return this.mensagemService.popupFormularioInvalido();
    }

    this.authService.entrarNaConta(this.form.value, this.carregar).subscribe(_ => {
      this.cookiesService.salvarDados(_);
      this.buscarConta();
    });
  }

  private buscarConta(): void {
    this.usuarioService.get(this.carregar).subscribe(usuario => {
      this.cookiesService.salvarUsuario(usuario);

      switch (usuario.tipo) {
        case 'Aluno':
          this.utilService.redirecionar('/');
          break;
        case 'Mentor':
          this.utilService.redirecionar(`/mentores/${usuario.apelido}`);
          break;
        case 'Gestor':
          this.utilService.redirecionar('/');
          break;
        default:
          this.utilService.redirecionar('/');
      }
    });
  }
}
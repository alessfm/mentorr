import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@shared/services/auth.service';
import { CookiesService } from '@core/services/cookies.service';
import { DOMService } from '@core/services/dom.service';
import { FormService } from '@core/services/form.service';
import { UsuarioService } from '@shared/services/usuario.service';

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
    private domService: DOMService,
    private formBuilder: FormBuilder,
    private formService: FormService,
    private usuarioService: UsuarioService
  ) {
    this.form = this.formBuilder.group({
      apelido: [null, Validators.required],
      senha: [null, Validators.required]
    })
  }

  entrar(): void {
    this.formService.validarFormEChamarFuncao(this.form);
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
          this.domService.redirecionar('/mentor/mentorias');
          break;
        default:
          this.domService.redirecionar('/');
      }
    });
  }
}

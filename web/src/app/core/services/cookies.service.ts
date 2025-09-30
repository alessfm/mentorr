import { Injectable } from '@angular/core';
import { PosLogin } from '@shared/models/auth.model';
import { Usuario } from '@shared/models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class CookiesService {

  salvarDados(dados: PosLogin) {
    sessionStorage.setItem('token', dados.token);
    sessionStorage.setItem('profile', dados.profile);
    sessionStorage.setItem('roles', dados.authorities.map(_ => _.authority).toString());
  }

  salvarUsuario(usuario: Usuario) {
    const json = JSON.stringify(usuario);
    sessionStorage.setItem('usuario', btoa(json));
  }

  get token(): string | null {
    return sessionStorage.getItem('token');
  }

  get profile(): string | null {
    return sessionStorage.getItem('profile');
  }

  get roles(): string[] {
    const roles = sessionStorage.getItem('roles');
    return !!roles ? roles.split(',') : [];
  }

  get usuario(): Usuario | null {
    const usuario = sessionStorage.getItem('usuario');
    return !!usuario ? JSON.parse(atob(usuario)) : null;
  }

  /**
   * @description Valida se o usuário possui uma das roles desejadas
   * @example this.cookiesService.compararRoles(['ROLE_ALUNO', 'ROLE_MENTOR', 'ROLE_GESTAO'])
   */
  compararRoles(lista: string[]): boolean {
    return this.roles.some(r => lista.includes(r));
  }
}

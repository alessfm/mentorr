import { TipoUsuario } from '../enums/tipo-usuario.enum';

export interface Login {
  apelido: string;
  senha: string
}

export interface PosLogin {
  token: string;
  profile: TipoUsuario;
  authorities: [{ authority: string }];
}
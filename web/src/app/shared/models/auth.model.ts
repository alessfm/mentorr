import { TipoUsuario } from '../enums/tipo-usuario.enum';

export interface Login {
  cpf: string;
  senha: string
}

export interface PosLogin {
  token: string;
  profile: TipoUsuario;
  authorities: any[];
}
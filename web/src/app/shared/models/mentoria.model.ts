import { Plano } from '@modules/mentor/models/plano.model';

export interface Mentoria {
  id: number;
  status: string;
  descricaoStatus: string;
  dataInicio: string;
  dataFim: string;

  nomeAluno?: string;
  apelidoAluno?: string;
  fotoAluno?: string;

  nomeMentor?: string;
  apelidoMentor?: string;
  fotoMentor?: string;

  plano: Plano;
  mensagens: MensagemMentoria[];

  //aux
  aberta?: boolean;
}

export interface MensagemMentoria {
  criadaEm: string;
  corpo: string;
  vista: boolean;

  nomeUsuario: string;
  apelidoUsuario: string;
  fotoUsuario: string;
}

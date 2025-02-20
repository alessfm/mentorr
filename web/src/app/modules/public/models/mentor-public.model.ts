import { Tag } from '@shared/models/tag.model';

export interface MentorBusca {
  nome: string;
  apelido: string;
  foto: string;
  descricao: string;
  cargo: string;
  empresa: string;
  dataInicio: string;
  tags: Tag[];
  nota?: number;
  valor?: number;
}

export interface MentorPublic {
  nome: string;
  apelido: string;
  descricao: string;
  cargo: string;
  empresa: string;
  dataInicio: string;
  ativo: boolean;
  // planos: Plano[];
  // horarios: Horario[];
  tags: Tag[];
}
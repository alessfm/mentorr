import { Plano } from './plano.model';
import { Horario } from '../../modules/mentor/models/horario.model';
import { Tag } from '@shared/models/tag.model';

export interface Mentor {
  id: number;
  nome: string;
  apelido: string;
  foto: string;
  descricao: string;
  cargo: string;
  empresa: string;
  dataInicio: string;
  ativo: boolean;
  planos: Plano[];
  horarios: Horario[];
  tags: Tag[];
}

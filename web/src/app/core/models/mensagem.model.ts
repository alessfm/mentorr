import { TipoMensagem } from '@core/enums/tipo-mensagem.enum';

export interface Mensagem {
  titulo: string;
  corpo?: string;
  tipo?: TipoMensagem;
  textoSim?: string;
  textoNao?: string;
}
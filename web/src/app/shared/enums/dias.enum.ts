import { Enum } from '../models/enum.model';

enum Dias {
  SEGUNDA = 'SEGUNDA',
  TERCA = 'TERCA',
  QUARTA = 'QUARTA',
  QUINTA = 'QUINTA',
  SEXTA = 'SEXTA',
  SABADO = 'SABADO',
  DOMINGO = 'DOMINGO'
}

export const EnumDias: Enum[] = [
  { valor: Dias.SEGUNDA, label: 'Segunda-feira', codigo: 0 },
  { valor: Dias.TERCA, label: 'Terça-feira', codigo: 1 },
  { valor: Dias.QUARTA, label: 'Quarta-feira', codigo: 2 },
  { valor: Dias.QUINTA, label: 'Quinta-feira', codigo: 3 },
  { valor: Dias.SEXTA, label: 'Sexta-feira', codigo: 4 },
  { valor: Dias.SABADO, label: 'Sábado', codigo: 5 },
  { valor: Dias.DOMINGO, label: 'Domingo', codigo: 6 }
];
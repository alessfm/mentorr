export interface Usuario {
  id: number;
  nome: string;
  apelido: string;
  email: string;
  senha?: string;
  tipo: string;
}
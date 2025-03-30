export class Dias {

  lista: Dia[];

  constructor() {
    this.lista = [
      { codigo: 0, nome: 'Segunda-feira' },
      { codigo: 1, nome: 'Terça-feira' },
      { codigo: 2, nome: 'Quarta-feira' },
      { codigo: 3, nome: 'Quinta-feira' },
      { codigo: 4, nome: 'Sexta-feira' },
      { codigo: 5, nome: 'Sábado' },
      { codigo: 6, nome: 'Domingo' }
    ];
  }
}

interface Dia {
  codigo: number;
  nome: string;
}

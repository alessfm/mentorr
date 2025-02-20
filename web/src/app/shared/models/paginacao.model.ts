export interface Paginacao<T> {
  lista: T[];
  pagina: number;
  totalPaginas: number;
  totalRegistros: number;
}
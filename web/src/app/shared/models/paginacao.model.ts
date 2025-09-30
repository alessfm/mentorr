export interface Paginacao<T> {
  lista: T[];
  pagina: number;
  totalPorPagina: number;
  totalPaginas: number;
  totalRegistros: number;
}

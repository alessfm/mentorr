package com.projeto.mentorr.util;

import java.util.List;

import lombok.Data;

@Data
public class ListaPaginacaoDTO<T> {

	private Integer pagina;
	private Integer totalPaginas;
	private Integer totalRegistros;
	private Integer totalPorPagina;
	private List<T> lista;

	/**
	 * @param pagina = Página atual
	 * @param totalRegistros = Total de registros
	 * @param totalPorPagina = Total de registros por página
	 * @param lista = Lista de registros
	 */
	public ListaPaginacaoDTO(Integer pagina, Integer totalRegistros, Integer totalPorPagina, List<T> lista) {
		this.totalPaginas = (int) Math.ceil(totalRegistros.doubleValue() / totalPorPagina.doubleValue());
		this.totalRegistros = totalRegistros;
		this.totalPorPagina = totalPorPagina;
		this.pagina = (totalPaginas <= 1) ? 1 : pagina;
		this.lista = lista;
	}

}

package com.projeto.mentorr.util;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ListaPaginacaoDTO {

	private Integer pagina;
	private Integer totalPaginas;
	private Integer totalRegistros;
	private List<?> lista;
	
	/**
	 * @param pagina = Página atual 
	 * @param totalRegistros = Total de registros sem paginação
	 * @param totalPorPagina = Total de registros por pagina
	 * @param lista = Lista de registros
	 */
	public ListaPaginacaoDTO(Integer pagina, Integer totalRegistros, Integer totalPorPagina, List<?> lista) {
		this.totalPaginas = (int) Math.ceil(totalRegistros.doubleValue() / totalPorPagina.doubleValue());
		this.totalRegistros = totalRegistros;
		this.pagina = (totalPaginas == 1) ? 1 : pagina;
		this.lista = lista;
	}
	
}
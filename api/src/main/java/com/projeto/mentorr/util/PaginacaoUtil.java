package com.projeto.mentorr.util;

public class PaginacaoUtil {

	public static Integer validarPagina(Long totalRegistros, Integer totalPorPagina, Integer pagina) {
		int totalPaginas = (int) Math.ceil(totalRegistros.doubleValue() / totalPorPagina.doubleValue());
		return pagina > totalPaginas ? totalPaginas : pagina;
	}

	public static Integer gerarOffset(Long totalRegistros, Integer totalPorPagina, Integer pagina) {
		return totalRegistros <= totalPorPagina ? 0 : totalPorPagina * (pagina - 1);
	}

}

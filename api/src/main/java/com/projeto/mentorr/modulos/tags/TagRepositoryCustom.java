package com.projeto.mentorr.modulos.tags;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface TagRepositoryCustom {

	ListaPaginacaoDTO buscarTags(String nome, Integer pagina, Integer totalPorPagina);
	
}
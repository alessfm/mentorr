package com.projeto.mentorr.modulos.tags;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface TagRepositoryCustom {

	ListaPaginacaoDTO buscarTags(String nome, Integer pagina, Integer totalPorPagina);
	
	List<Tag> buscarTagsDestaque();
	
}
package com.projeto.mentorr.modulos.tags;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface TagService {
		
	ListaPaginacaoDTO buscarTags(String nome, Integer pagina, Integer totalPorPagina);
	
	List<Tag> buscarTagsDestaque();

	Tag buscarPorId(Long idTag);

	Tag salvar(CadastroTagDTO DTO);

	Tag atualizar(Long idTag, CadastroTagDTO DTO);

	void excluir(Long idTag);

}

package com.projeto.mentorr.modulos.tags;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface TagService {
		
	ListaPaginacaoDTO buscarTags(String nome, Integer pagina, Integer totalPorPagina);

	Tag buscarPorId(Long idTag);

	Tag salvar(CadastroTagDTO DTO);

	Tag atualizar(Long idTag, CadastroTagDTO DTO);

	void excluir(Long idTag);

}

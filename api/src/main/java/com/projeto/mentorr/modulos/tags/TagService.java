package com.projeto.mentorr.modulos.tags;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface TagService {

	ListaPaginacaoDTO<Tag> buscarTags(String nome, Integer pagina, Integer totalPorPagina);

	List<Tag> buscarTagsDestaque();

	Tag buscarPorId(Long id);

	Tag salvar(CadastroTagDTO tagDTO);

	Tag atualizar(Long id, CadastroTagDTO tagDTO);

	void excluir(Long id);

}

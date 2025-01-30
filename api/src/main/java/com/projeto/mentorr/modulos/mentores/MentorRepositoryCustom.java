package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface MentorRepositoryCustom {

	ListaPaginacaoDTO buscarMentores(String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina);

	MentorDTO buscarPorApelido(String apelido);
	
	MentorDTO buscarMentorLogado(String apelido);

}

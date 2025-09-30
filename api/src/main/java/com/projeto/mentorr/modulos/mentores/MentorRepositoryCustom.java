package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface MentorRepositoryCustom {

	ListaPaginacaoDTO<MentorDTO> buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina);

	List<MentorDTO> buscarMentoresDestaque();

	MentorDTO buscarMentorPorApelido(String apelido);

}

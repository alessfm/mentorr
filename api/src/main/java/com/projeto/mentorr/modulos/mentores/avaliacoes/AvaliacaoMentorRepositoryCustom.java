package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

public interface AvaliacaoMentorRepositoryCustom {

	List<AvaliacaoMentorDTO> buscarAvaliacoesMentor(Long idMentor);

	List<AvaliacaoMentorDTO> buscarAvaliacoesMentorPublic(String apelido);

}

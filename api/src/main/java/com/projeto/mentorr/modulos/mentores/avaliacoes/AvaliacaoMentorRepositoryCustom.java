package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

public interface AvaliacaoMentorRepositoryCustom {

	List<AvaliacaoMentorDTO> buscarAvaliacoesPorMentor(Long idMentor);
	
	List<AvaliacaoMentorDTO> buscarAvaliacoesPorApelidoMentor(String apelido);
	
}
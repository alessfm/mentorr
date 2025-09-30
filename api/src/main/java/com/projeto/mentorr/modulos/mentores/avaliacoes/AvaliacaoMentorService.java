package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

public interface AvaliacaoMentorService {

	List<AvaliacaoMentorDTO> buscarAvaliacoesMentor(Long idMentor);

	List<AvaliacaoMentorDTO> buscarAvaliacoesMentorLogado();

}

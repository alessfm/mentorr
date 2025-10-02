package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;

public interface PlanoMentorRepositoryCustom {

	List<PlanoMentorDTO> buscarPlanosMentor(Long idMentor);

	List<PlanoMentorDTO> buscarPlanosMentorPublic(String apelido);

	PlanoMentorDTO buscarPlanoMentoria(Long idMentoria);

}

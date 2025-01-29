package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;

public interface PlanoMentorRepositoryCustom {

	List<PlanoMentorDTO> buscarPlanosPorMentor(Long idMentor);

	List<PlanoMentorDTO> buscarPlanosPorApelidoMentor(String apelido);

}
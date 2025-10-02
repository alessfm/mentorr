package com.projeto.mentorr.modulos.mentorias;

import java.util.List;

public interface MentoriaRepositoryCustom {

	List<MentoriaDTO> buscarMentoriasAluno(Long idAluno);

	List<MentoriaDTO> buscarMentoriasMentor(Long idMentor);

}

package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.List;

public interface HorarioMentorRepositoryCustom {

	List<HorarioMentorDTO> buscarHorariosMentor(Long idMentor);

}

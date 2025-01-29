package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.List;

public interface HorarioMentorRepositoryCustom {

	List<HorarioMentorDTO> buscarHorariosPorMentor(Long idMentor);

	List<HorarioMentorDTO> buscarHorariosPorApelidoMentor(String apelido);

}
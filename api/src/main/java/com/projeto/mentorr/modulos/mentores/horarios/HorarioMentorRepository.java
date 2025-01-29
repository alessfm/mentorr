package com.projeto.mentorr.modulos.mentores.horarios;

import java.time.DayOfWeek;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioMentorRepository extends JpaRepository<HorarioMentor, Long>, HorarioMentorRepositoryCustom {

	Optional<HorarioMentor> findByIdAndMentor_Id(Long idHorario, Long idMentor);
	
	Optional<HorarioMentor> findByDiaAndMentor_Id(DayOfWeek dia, Long idMentor);

}

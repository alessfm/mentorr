package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.usuarios.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class HorarioMentorRepositoryImpl implements HorarioMentorRepositoryCustom {
	
	private final EntityManager entityManager;

	@Override
	public List<HorarioMentorDTO> buscarHorariosPorMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<HorarioMentorDTO> cq = cb.createQuery(HorarioMentorDTO.class);
		Root<HorarioMentor> horario = cq.from(HorarioMentor.class);
		
		Join<HorarioMentor, Mentor> mentor = horario.join("mentor", JoinType.INNER);

		cq.multiselect(
			horario.get("id"),
			horario.get("dia"),
			horario.get("horaInicio"),
			horario.get("horaFim")
		);
		
		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.asc(horario.get("dia")));
		
		return entityManager.createQuery(cq).getResultList();
	}
	
	@Override
	public List<HorarioMentorDTO> buscarHorariosPorApelidoMentor(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<HorarioMentorDTO> cq = cb.createQuery(HorarioMentorDTO.class);
		Root<HorarioMentor> horario = cq.from(HorarioMentor.class);
		
		Join<HorarioMentor, Mentor> mentor = horario.join("mentor", JoinType.INNER);
		Join<Usuario, Mentor> usuario = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			horario.get("dia"),
			horario.get("horaInicio"),
			horario.get("horaFim")
		);
		
		cq.where(cb.equal(usuario.get("apelido"), apelido));
		cq.orderBy(cb.asc(horario.get("dia")));
		
		return entityManager.createQuery(cq).getResultList();
	}

}

package com.projeto.mentorr.modulos.mentores.planos;

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
public class PlanoMentorRepositoryImpl implements PlanoMentorRepositoryCustom {
	
	private final EntityManager entityManager;

	@Override
	public List<PlanoMentorDTO> buscarPlanosPorMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoMentorDTO> cq = cb.createQuery(PlanoMentorDTO.class);
		Root<PlanoMentor> plano = cq.from(PlanoMentor.class);
		
		Join<PlanoMentor, Mentor> mentor = plano.join("mentor", JoinType.INNER);

		cq.multiselect(
			plano.get("id"),
			plano.get("valor")
		);
		
		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.asc(plano.get("valor")));
		
		return entityManager.createQuery(cq).getResultList();
	}
	
	@Override
	public List<PlanoMentorDTO> buscarPlanosPorApelidoMentor(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoMentorDTO> cq = cb.createQuery(PlanoMentorDTO.class);
		Root<PlanoMentor> plano = cq.from(PlanoMentor.class);
		
		Join<PlanoMentor, Mentor> mentor = plano.join("mentor", JoinType.INNER);
		Join<Usuario, Mentor> usuario = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			plano.get("valor")
		);
		
		cq.where(cb.equal(usuario.get("apelido"), apelido));
		cq.orderBy(cb.asc(plano.get("valor")));
		
		return entityManager.createQuery(cq).getResultList();
	}

}

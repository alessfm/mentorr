package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.mentorias.Mentoria;
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
	public List<PlanoMentorDTO> buscarPlanosMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoMentorDTO> cq = cb.createQuery(PlanoMentorDTO.class);
		Root<PlanoMentor> plano = cq.from(PlanoMentor.class);

		Join<PlanoMentor, Mentor> mentor = plano.join("mentor", JoinType.INNER);

		cq.multiselect(
			plano.get("id"),
			plano.get("tipo"),
			plano.get("valor"),
			plano.get("descricao"),
			plano.get("totalChamadas"),
			plano.get("duracaoChamada"),
			plano.get("tempoResposta")
		);

		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.asc(plano.get("valor")));

		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<PlanoMentorDTO> buscarPlanosMentorPublic(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoMentorDTO> cq = cb.createQuery(PlanoMentorDTO.class);
		Root<PlanoMentor> plano = cq.from(PlanoMentor.class);

		Join<PlanoMentor, Mentor> mentor = plano.join("mentor", JoinType.INNER);
		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			plano.get("codigo"),
			plano.get("tipo"),
			plano.get("valor"),
			plano.get("descricao"),
			plano.get("totalChamadas"),
			plano.get("duracaoChamada"),
			plano.get("tempoResposta")
		);

		cq.where(cb.equal(usuario.get("apelido"), apelido));
		cq.orderBy(cb.asc(plano.get("valor")));

		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public PlanoMentorDTO buscarPlanoMentoria(Long idMentoria) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoMentorDTO> cq = cb.createQuery(PlanoMentorDTO.class);
		Root<Mentoria> mentoria = cq.from(Mentoria.class);

		Join<Mentoria, PlanoMentor> plano = mentoria.join("plano", JoinType.INNER);

		cq.multiselect(
			plano.get("id"),
			plano.get("tipo"),
			plano.get("valor"),
			plano.get("descricao"),
			plano.get("totalChamadas"),
			plano.get("duracaoChamada"),
			plano.get("tempoResposta")
		);

		cq.where(cb.equal(mentoria.get("id"), idMentoria));

		return entityManager.createQuery(cq).getSingleResult();
	}

}

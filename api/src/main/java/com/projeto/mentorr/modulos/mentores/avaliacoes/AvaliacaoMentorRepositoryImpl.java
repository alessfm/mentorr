package com.projeto.mentorr.modulos.mentores.avaliacoes;

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
public class AvaliacaoMentorRepositoryImpl implements AvaliacaoMentorRepositoryCustom {

	private final EntityManager entityManager;

	@Override
	public List<AvaliacaoMentorDTO> buscarAvaliacoesMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AvaliacaoMentorDTO> cq = cb.createQuery(AvaliacaoMentorDTO.class);
		Root<AvaliacaoMentor> avaliacao = cq.from(AvaliacaoMentor.class);

		Join<AvaliacaoMentor, Mentor> mentor = avaliacao.join("mentor", JoinType.INNER);
		Join<AvaliacaoMentor, Usuario> aluno = avaliacao.join("aluno", JoinType.INNER);

		cq.multiselect(
			aluno.get("nome"),
			avaliacao.get("nota"),
			avaliacao.get("data"),
			avaliacao.get("comentario")
		);

		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.desc(avaliacao.get("nota")));

		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<AvaliacaoMentorDTO> buscarAvaliacoesMentorPublic(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AvaliacaoMentorDTO> cq = cb.createQuery(AvaliacaoMentorDTO.class);
		Root<AvaliacaoMentor> avaliacao = cq.from(AvaliacaoMentor.class);

		Join<AvaliacaoMentor, Usuario> aluno = avaliacao.join("aluno", JoinType.INNER);
		Join<AvaliacaoMentor, Mentor> mentor = avaliacao.join("mentor", JoinType.INNER);
		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			aluno.get("nome"),
			avaliacao.get("nota"),
			avaliacao.get("data"),
			avaliacao.get("comentario")
		);

		cq.where(cb.equal(usuario.get("apelido"), apelido));
		cq.orderBy(cb.desc(avaliacao.get("nota")));

		return entityManager.createQuery(cq).getResultList();
	}

}

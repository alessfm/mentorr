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
	public List<AvaliacaoMentorDTO> buscarAvaliacoesPorMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AvaliacaoMentorDTO> cq = cb.createQuery(AvaliacaoMentorDTO.class);
		Root<AvaliacaoMentor> avaliacao = cq.from(AvaliacaoMentor.class);
		
		Join<Mentor, AvaliacaoMentor> mentor = avaliacao.join("mentor", JoinType.INNER);
		Join<Usuario, AvaliacaoMentor> aluno = avaliacao.join("aluno", JoinType.INNER);

		cq.multiselect(
			avaliacao.get("nota"),
			avaliacao.get("data"),
			avaliacao.get("comentario"),
			aluno.get("nome")
		);
		
		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.desc(avaliacao.get("nota")));
		
		return entityManager.createQuery(cq).getResultList();
	}
	
	@Override
	public List<AvaliacaoMentorDTO> buscarAvaliacoesPorApelidoMentor(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AvaliacaoMentorDTO> cq = cb.createQuery(AvaliacaoMentorDTO.class);
		Root<AvaliacaoMentor> avaliacao = cq.from(AvaliacaoMentor.class);
		
		Join<Usuario, AvaliacaoMentor> aluno = avaliacao.join("aluno", JoinType.INNER);
		
		Join<Mentor, AvaliacaoMentor> mentor = avaliacao.join("mentor", JoinType.INNER);
		Join<Usuario, Mentor> usuario = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			avaliacao.get("nota"),
			avaliacao.get("data"),
			avaliacao.get("comentario"),
			aluno.get("nome")
		);
		
		cq.where(cb.equal(usuario.get("apelido"), apelido));
		cq.orderBy(cb.desc(avaliacao.get("nota")));
		
		return entityManager.createQuery(cq).getResultList();
	}

}

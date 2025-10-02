package com.projeto.mentorr.modulos.mentorias;

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
public class MentoriaRepositoryImpl implements MentoriaRepositoryCustom {

	private final EntityManager entityManager;

	@Override
	public List<MentoriaDTO> buscarMentoriasAluno(Long idAluno) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MentoriaDTO> cq = cb.createQuery(MentoriaDTO.class);
		Root<Mentoria> mentoria = cq.from(Mentoria.class);

		Join<Mentoria, Usuario> aluno = mentoria.join("aluno", JoinType.INNER);
		Join<Mentoria, Mentor> mentor = mentoria.join("mentor", JoinType.INNER);
		Join<Mentor, Usuario> usuarioMentor = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			mentoria.get("id"),
			mentoria.get("status"),
			mentoria.get("dataInicio"),
			mentoria.get("dataFim"),

			usuarioMentor.get("nome"),
			usuarioMentor.get("apelido"),
			mentor.get("foto")
		);

		cq.where(cb.equal(aluno.get("id"), idAluno));
		cq.orderBy(cb.asc(mentoria.get("dataInicio")));

		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<MentoriaDTO> buscarMentoriasMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MentoriaDTO> cq = cb.createQuery(MentoriaDTO.class);
		Root<Mentoria> mentoria = cq.from(Mentoria.class);

		Join<Mentoria, Usuario> aluno = mentoria.join("aluno", JoinType.INNER);
		Join<Mentoria, Mentor> mentor = mentoria.join("mentor", JoinType.INNER);

		cq.multiselect(
			mentoria.get("id"),
			mentoria.get("status"),
			mentoria.get("dataInicio"),
			mentoria.get("dataFim"),

			aluno.get("nome"),
			aluno.get("apelido")
		);

		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.asc(mentoria.get("dataInicio")));

		return entityManager.createQuery(cq).getResultList();
	}

}

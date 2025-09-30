package com.projeto.mentorr.modulos.mentores.tags;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.tags.Tag;
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
public class TagMentorRepositoryImpl implements TagMentorRepositoryCustom {

	private final EntityManager entityManager;

	@Override
	public List<Tag> buscarTagsMentor(Long idMentor) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);

		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		Join<TagMentor, Mentor> mentor = tagMentor.join("mentor", JoinType.INNER);

		cq.select(tag);

		cq.where(cb.equal(mentor.get("id"), idMentor));
		cq.orderBy(cb.asc(tagMentor.get("ordem")));

		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<Tag> buscarTagsPorApelido(String apelido, Integer totalTags) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);

		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		Join<TagMentor, Mentor> mentor = tagMentor.join("mentor", JoinType.INNER);
		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		cq.select(tag);

		cq.where(cb.equal(usuario.get("apelido"), apelido));
		cq.orderBy(cb.asc(tagMentor.get("ordem")));

		return entityManager.createQuery(cq).setMaxResults(totalTags).getResultList();
	}

}

package com.projeto.mentorr.modulos.mentores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.core.exception.InternalErrorException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.tags.TagMentor;
import com.projeto.mentorr.modulos.tags.Tag;
import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.util.ListaPaginacaoDTO;
import com.projeto.mentorr.util.PaginacaoUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MentorRepositoryImpl implements MentorRepositoryCustom {

	private final EntityManager entityManager;

	@Override
	public ListaPaginacaoDTO<MentorDTO> buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MentorDTO> cq = cb.createQuery(MentorDTO.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);

		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		Join<TagMentor, Mentor> mentor = tagMentor.join("mentor", JoinType.INNER);
		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (texto != null) {
			predicates = criarFiltroGlobal(cb, tag, mentor, texto);
		} else {
			predicates = criarFiltroPorParametros(cb, tag, mentor, cargo, empresa, tags);			
		}

		cq.multiselect(
			usuario.get("nome"),
			usuario.get("apelido"),
			mentor.get("foto"),
			mentor.get("descricao"),
			mentor.get("cargo"),
			mentor.get("empresa"),
			mentor.get("dataInicio"),
			mentor.get("nota")
		).distinct(true);

		cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(mentor.get("nota")), cb.asc(usuario.get("nome")));

        Long totalRegistros = buscarTotalMentores(texto, cargo, empresa, tags);

		pagina = PaginacaoUtil.validarPagina(totalRegistros, totalPorPagina, pagina);
		Integer offset = PaginacaoUtil.gerarOffset(totalRegistros, totalPorPagina, pagina);

		List<MentorDTO> mentores = entityManager.createQuery(cq)
				.setFirstResult(offset)
		        .setMaxResults(totalPorPagina)
		        .getResultList();

		return new ListaPaginacaoDTO<MentorDTO>(pagina, totalRegistros.intValue(), totalPorPagina, mentores);
	}

	private List<Predicate> criarFiltroPorParametros(
		CriteriaBuilder cb, Join<TagMentor, Tag> tag, Join<TagMentor, Mentor> mentor, 
		String cargo, String empresa, List<Long> tags
	) {
		List<Predicate> predicates = new ArrayList<>();

		if (cargo != null) {
			predicates.add(cb.like(cb.lower(mentor.get("cargo")), "%" + cargo.toLowerCase() + "%"));
		}

		if (empresa != null) {
			predicates.add(cb.like(cb.lower(mentor.get("empresa")), "%" + empresa.toLowerCase() + "%"));
		}

		if (tags != null) {
        	predicates.add(tag.get("id").in(tags));
        }

		predicates.add(cb.isTrue(mentor.get("ativo")));

		return predicates;
	}

	private List<Predicate> criarFiltroGlobal(
		CriteriaBuilder cb, Join<TagMentor, Tag> tag, Join<TagMentor, Mentor> mentor, String texto
	) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(
			cb.or(
				cb.like(cb.lower(mentor.get("cargo")), "%" + texto.toLowerCase() + "%"),
				cb.like(cb.lower(mentor.get("empresa")), "%" + texto.toLowerCase() + "%"),
				cb.like(cb.lower(tag.get("nome")),  "%" + texto.toLowerCase() + "%")
			)
		);

		predicates.add(cb.isTrue(mentor.get("ativo")));

		return predicates;
	}

	private Long buscarTotalMentores(String texto, String cargo, String empresa, List<Long> tags) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);

		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		Join<TagMentor, Mentor> mentor = tagMentor.join("mentor", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (texto != null) {
			predicates = criarFiltroGlobal(cb, tag, mentor, texto);
		} else {
			predicates = criarFiltroPorParametros(cb, tag, mentor, cargo, empresa, tags);			
		}

		cq.multiselect(cb.countDistinct(mentor.get("id")));
        cq.where(predicates.toArray(new Predicate[0]));

        try {
        	return entityManager.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}

	@Override
	public List<MentorDTO> buscarMentoresDestaque() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MentorDTO> cq = cb.createQuery(MentorDTO.class);
		Root<Mentor> mentor = cq.from(Mentor.class);

		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.isTrue(mentor.get("ativo")));
		predicates.add(cb.isNotNull(mentor.get("nota")));

		cq.multiselect(
			usuario.get("nome"),
			usuario.get("apelido"),
			mentor.get("foto"),
			mentor.get("cargo"),
			mentor.get("nota")
		);

		cq.where(predicates.toArray(new Predicate[0]));
		cq.orderBy(cb.desc(mentor.get("nota")));

		return entityManager.createQuery(cq).setMaxResults(6).getResultList();
	}

	@Override
	public MentorDTO buscarMentorPorApelido(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MentorDTO> cq = cb.createQuery(MentorDTO.class);
		Root<Mentor> mentor = cq.from(Mentor.class);

		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(usuario.get("apelido"), apelido));
		predicates.add(cb.isTrue(mentor.get("ativo")));

		cq.multiselect(
			mentor.get("id"),
			usuario.get("nome"),
			usuario.get("apelido"),
			mentor.get("foto"),
			mentor.get("descricao"),
			mentor.get("cargo"),
			mentor.get("empresa"),
			mentor.get("dataInicio"),
			mentor.get("nota")
		);

		cq.where(predicates.toArray(new Predicate[0]));

		try {
			return entityManager.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Mentor não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalErrorException("Não foi possível exibir os dados do mentor");
		}
	}

}

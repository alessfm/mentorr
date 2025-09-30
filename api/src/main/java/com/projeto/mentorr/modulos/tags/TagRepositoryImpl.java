package com.projeto.mentorr.modulos.tags;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.modulos.mentores.tags.TagMentor;
import com.projeto.mentorr.util.ListaPaginacaoDTO;
import com.projeto.mentorr.util.PaginacaoUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TagRepositoryImpl implements TagRepositoryCustom {

	private final EntityManager entityManager;

	@Override
	public ListaPaginacaoDTO<Tag> buscarTags(String nome, Integer pagina, Integer totalPorPagina) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<Tag> tag = cq.from(Tag.class);

		List<Predicate> predicates = criarFiltroPorParametros(cb, tag, nome);

		cq.select(tag).distinct(true);

		cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(tag.get("nome")));

        Long totalRegistros = buscarTotalTags(nome);

		pagina = PaginacaoUtil.validarPagina(totalRegistros, totalPorPagina, pagina);
		Integer offset = PaginacaoUtil.gerarOffset(totalRegistros, totalPorPagina, pagina);

		List<Tag> tags = entityManager.createQuery(cq)
				.setFirstResult(offset)
		        .setMaxResults(totalPorPagina)
		        .getResultList();

		return new ListaPaginacaoDTO<Tag>(pagina, totalRegistros.intValue(), totalPorPagina, tags);
	}

	private List<Predicate> criarFiltroPorParametros(CriteriaBuilder cb, Root<Tag> tag, String nome) {
		List<Predicate> predicates = new ArrayList<>();

		if (nome != null) {
			predicates.add(cb.like(cb.lower(tag.get("nome")), "%" + nome.toLowerCase() + "%"));
		}

		return predicates;
	}

	private Long buscarTotalTags(String nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Tag> tag = cq.from(Tag.class);

		List<Predicate> predicates = criarFiltroPorParametros(cb, tag, nome);

		cq.multiselect(cb.countDistinct(tag.get("id")));
        cq.where(predicates.toArray(new Predicate[0]));

        try {
        	return entityManager.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}

	public List<Tag> buscarTagsDestaque() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);

		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);

        cq.select(tag);
        cq.groupBy(tag.get("id"));
        cq.orderBy(cb.desc(cb.count(tag)));

        return entityManager.createQuery(cq).setMaxResults(5).getResultList();
	}

}

package com.projeto.mentorr.modulos.tags;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TagRepositoryImpl implements TagRepositoryCustom {
	
	private final EntityManager entityManager;

	@Override
	public ListaPaginacaoDTO buscarTags(String nome, Integer pagina, Integer totalPorPagina) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<Tag> tag = cq.from(Tag.class);

		List<Predicate> predicates = criarFiltroPorParametros(cb, tag, nome);
		
		cq.select(tag);
		
		cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(tag.get("nome")));
		
		Long totalRegistros = totalTags(nome);
		
		Integer offset = (totalRegistros < totalPorPagina) ? 0 : totalPorPagina * (pagina - 1);
		
		List<Tag> tags = entityManager.createQuery(cq)
		        .setFirstResult(offset)
		        .setMaxResults(totalPorPagina)
		        .getResultList();
		
		return new ListaPaginacaoDTO(pagina, totalRegistros.intValue(), totalPorPagina, tags);
	}
	
	private List<Predicate> criarFiltroPorParametros(CriteriaBuilder cb, Root<Tag> tag, String nome) {
		List<Predicate> predicates = new ArrayList<>();

		if (nome != null) {
			predicates.add(cb.like(cb.lower(tag.get("nome")), "%" + nome.toLowerCase() + "%"));
		}
		
		return predicates;
	}
	
	private Long totalTags(String nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Tag> tag = cq.from(Tag.class);
		
		List<Predicate> predicates = criarFiltroPorParametros(cb, tag, nome);
		
        cq.select(cb.count(tag));
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
		Root<Tag> tag = cq.from(Tag.class);
		
//		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		
        cq.select(tag);
        
        return entityManager.createQuery(cq).setMaxResults(5).getResultList();
	}

}

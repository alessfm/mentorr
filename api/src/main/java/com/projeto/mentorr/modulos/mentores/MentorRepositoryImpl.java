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
	public ListaPaginacaoDTO buscarMentores(String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ListaMentoresDTO> cq = cb.createQuery(ListaMentoresDTO.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);
		
		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		Join<TagMentor, Mentor> mentor = tagMentor.join("mentor", JoinType.INNER);
		Join<Mentor, Usuario> usuario = mentor.join("usuario", JoinType.INNER);

		List<Predicate> predicates = criarFiltroBuscarMentores(cb, tag, mentor, cargo, empresa, tags);
		
		cq.multiselect(
			usuario.get("nome"),
			usuario.get("apelido"),
			mentor.get("descricao"),
			mentor.get("cargo"),
			mentor.get("empresa"),
			mentor.get("dataInicio")
		).distinct(true);
		
		cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(usuario.get("nome")));
		
		Long totalRegistros = totalMentores(cargo, empresa, tags);
		
		Integer offset = (totalRegistros < totalPorPagina) ? 0 : totalPorPagina * (pagina - 1);
		
		List<ListaMentoresDTO> mentores = entityManager.createQuery(cq)
		        .setFirstResult(offset)
		        .setMaxResults(totalPorPagina)
		        .getResultList();
		
		return new ListaPaginacaoDTO(pagina, totalRegistros.intValue(), totalPorPagina, mentores);
	}
	
	private List<Predicate> criarFiltroBuscarMentores(
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
		
		return predicates;
	}
	
	private Long totalMentores(String cargo, String empresa, List<Long> tags) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TagMentor> tagMentor = cq.from(TagMentor.class);
		
		Join<TagMentor, Tag> tag = tagMentor.join("tag", JoinType.INNER);
		Join<TagMentor, Mentor> mentor = tagMentor.join("mentor", JoinType.INNER);

		List<Predicate> predicates = criarFiltroBuscarMentores(cb, tag, mentor, cargo, empresa, tags);

        cq.select(cb.countDistinct(mentor));
        cq.where(predicates.toArray(new Predicate[0]));
        
        try {
        	return entityManager.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}
	
	@Override
	public MentorDTO buscarPorApelido(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MentorDTO> cq = cb.createQuery(MentorDTO.class);
		Root<Mentor> mentor = cq.from(Mentor.class);
		
		Join<Usuario, Mentor> usuario = mentor.join("usuario", JoinType.INNER);

		cq.multiselect(
			usuario.get("nome"),
			usuario.get("apelido"),
			mentor.get("descricao"),
			mentor.get("cargo"),
			mentor.get("empresa"),
			mentor.get("dataInicio")
		);
		
		cq.where(cb.equal(usuario.get("apelido"), apelido));
		
		try {
			return entityManager.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Mentor não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalErrorException("Não foi possível exibir os dados do mentor. Erro: " + e.getLocalizedMessage());
		}
	}

}

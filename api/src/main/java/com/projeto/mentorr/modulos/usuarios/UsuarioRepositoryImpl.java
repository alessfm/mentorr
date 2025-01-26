package com.projeto.mentorr.modulos.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.core.exception.InternalErrorException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.util.ListaPaginacaoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {
	
	private final EntityManager entityManager;

	@Override
	public ListaPaginacaoDTO buscarUsuarios(String nome, Boolean ativo, Integer pagina, Integer totalPorPagina) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ListaUsuariosDTO> cq = cb.createQuery(ListaUsuariosDTO.class);
		Root<Usuario> usuario = cq.from(Usuario.class);

		List<Predicate> predicates = criarFiltroBuscarUsuarios(cb, usuario, nome, ativo);
		
		cq.multiselect(
			usuario.get("id"),
			usuario.get("nome"),
			usuario.get("apelido"),
			usuario.get("email"),
			usuario.get("tipo")
		);
		
		cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(usuario.get("nome")));
		
		Long totalRegistros = totalUsuarios(nome, ativo);
		
		Integer offset = (totalRegistros < totalPorPagina) ? 0 : totalPorPagina * (pagina - 1);
		
		List<ListaUsuariosDTO> usuarios = entityManager.createQuery(cq)
		        .setFirstResult(offset)
		        .setMaxResults(totalPorPagina)
		        .getResultList();
		
		return new ListaPaginacaoDTO(pagina, totalRegistros.intValue(), totalPorPagina, usuarios);
	}
	
	private List<Predicate> criarFiltroBuscarUsuarios(CriteriaBuilder cb, Root<Usuario> usuario, String nome, Boolean ativo) {
		List<Predicate> predicates = new ArrayList<>();

		if (nome != null) {
			predicates.add(cb.like(cb.lower(usuario.get("nome")), "%" + nome.toLowerCase() + "%"));
		}
		
		if (ativo != null) {
			predicates.add(cb.equal(usuario.get("ativo"), ativo));
		}
		
		return predicates;
	}
	
	private Long totalUsuarios(String nome, Boolean ativo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Usuario> usuario = cq.from(Usuario.class);
		
		List<Predicate> predicates = criarFiltroBuscarUsuarios(cb, usuario, nome, ativo);
		
        cq.select(cb.count(usuario));
        cq.where(predicates.toArray(new Predicate[0]));
        
        try {
        	return entityManager.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}
	
	@Override
	public UsuarioDTO buscarUsuarioPorApelido(String apelido) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioDTO> cq = cb.createQuery(UsuarioDTO.class);
		Root<Usuario> usuario = cq.from(Usuario.class);

		cq.multiselect(
			usuario.get("nome"),
			usuario.get("apelido"),
			usuario.get("email")
		);

		cq.where(cb.equal(usuario.get("apelido"), apelido));

		try {
			return entityManager.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Usuário não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalErrorException("Não foi possível exibir os dados do usuário. Erro: " + e.getLocalizedMessage());
		}
	}

}

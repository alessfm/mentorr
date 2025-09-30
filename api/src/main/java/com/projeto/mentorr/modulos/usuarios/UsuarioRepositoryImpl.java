package com.projeto.mentorr.modulos.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.projeto.mentorr.util.ListaPaginacaoDTO;
import com.projeto.mentorr.util.PaginacaoUtil;

import jakarta.persistence.EntityManager;
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
	public ListaPaginacaoDTO<UsuarioDTO> buscarUsuarios(
		String nome, String apelido, TipoUsuario tipo, Boolean ativo, Integer pagina, Integer totalPorPagina
	) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioDTO> cq = cb.createQuery(UsuarioDTO.class);
		Root<Usuario> usuario = cq.from(Usuario.class);

		List<Predicate> predicates = criarFiltroPorParametros(cb, usuario, nome, apelido, tipo, ativo);

		cq.multiselect(
			usuario.get("id"),
			usuario.get("nome"),
			usuario.get("apelido"),
			usuario.get("email"),
			usuario.get("tipo"),
			usuario.get("ativo"),
			usuario.get("excluido")
		).distinct(true);

		cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(usuario.get("nome")));

		Long totalRegistros = buscarTotalUsuarios(nome, apelido, tipo, ativo);

		pagina = PaginacaoUtil.validarPagina(totalRegistros, totalPorPagina, pagina);
		Integer offset = PaginacaoUtil.gerarOffset(totalRegistros, totalPorPagina, pagina);

		List<UsuarioDTO> usuarios = entityManager.createQuery(cq)
				.setFirstResult(offset)
		        .setMaxResults(totalPorPagina)
		        .getResultList();

		return new ListaPaginacaoDTO<UsuarioDTO>(pagina, totalRegistros.intValue(), totalPorPagina, usuarios);
	}

	private List<Predicate> criarFiltroPorParametros(
		CriteriaBuilder cb, Root<Usuario> usuario, String nome, String apelido, TipoUsuario tipo, Boolean ativo
	) {
		List<Predicate> predicates = new ArrayList<>();

		if (nome != null) {
			predicates.add(cb.like(cb.lower(usuario.get("nome")), "%" + nome.toLowerCase() + "%"));
		}

		if (apelido != null) {
			predicates.add(cb.like(cb.lower(usuario.get("apelido")), "%" + apelido.toLowerCase() + "%"));
		}

		if (tipo != null) {
			predicates.add(cb.equal(usuario.get("tipo"), tipo));
		}

		if (ativo != null) {
			predicates.add(cb.equal(usuario.get("ativo"), ativo));
		}

		return predicates;
	}

	private Long buscarTotalUsuarios(String nome, String apelido, TipoUsuario tipo, Boolean ativo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Usuario> usuario = cq.from(Usuario.class);

		List<Predicate> predicates = criarFiltroPorParametros(cb, usuario, nome, apelido, tipo, ativo);

		cq.multiselect(cb.countDistinct(usuario.get("id")));
        cq.where(predicates.toArray(new Predicate[0]));

        try {
        	return entityManager.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}

}

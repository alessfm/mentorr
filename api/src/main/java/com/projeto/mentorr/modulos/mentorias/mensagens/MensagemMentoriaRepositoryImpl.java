package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projeto.mentorr.modulos.mentorias.Mentoria;
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
public class MensagemMentoriaRepositoryImpl implements MensagemMentoriaRepositoryCustom {

	private final EntityManager entityManager;

	@Override
	public List<MensagemMentoriaDTO> buscarMensagensMentoria(Long idMentoria) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MensagemMentoriaDTO> cq = cb.createQuery(MensagemMentoriaDTO.class);
		Root<MensagemMentoria> mensagem = cq.from(MensagemMentoria.class);

		Join<MensagemMentoria, Mentoria> mentoria = mensagem.join("mentoria", JoinType.INNER);
		Join<MensagemMentoria, Usuario> usuario = mensagem.join("usuario", JoinType.INNER);

		cq.multiselect(
			mensagem.get("criadaEm"),
			mensagem.get("corpo"),
			mensagem.get("vista"),

			usuario.get("nome"),
			usuario.get("apelido")
//			usuario.get("foto"),
		);

		cq.where(cb.equal(mentoria.get("id"), idMentoria));
		cq.orderBy(cb.asc(mensagem.get("criadaEm")));

		return entityManager.createQuery(cq).getResultList();
	}

}

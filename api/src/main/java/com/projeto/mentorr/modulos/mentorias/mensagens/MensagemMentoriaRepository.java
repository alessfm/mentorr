package com.projeto.mentorr.modulos.mentorias.mensagens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemMentoriaRepository extends JpaRepository<MensagemMentoria, Long>, MensagemMentoriaRepositoryCustom {

}

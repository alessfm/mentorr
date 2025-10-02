package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.util.List;

public interface MensagemMentoriaRepositoryCustom {

	List<MensagemMentoriaDTO> buscarMensagensMentoria(Long idMentoria);

}

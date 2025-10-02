package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.util.List;

public interface MensagemMentoriaService {

	List<MensagemMentoriaDTO> buscarMensagensMentoria(Long idMentoria);

	void enviaMensagemMentoria(Long idMentoria, NovaMensagemDTO mensagemDTO);

}

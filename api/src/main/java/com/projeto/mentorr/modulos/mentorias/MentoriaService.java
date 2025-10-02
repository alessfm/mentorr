package com.projeto.mentorr.modulos.mentorias;

import java.util.List;

public interface MentoriaService {

	Mentoria buscarPorId(Long id);

	List<MentoriaDTO> buscarMentoriasAluno();

	List<MentoriaDTO> buscarMentoriasMentor();

	void solicitarMentoria(SolicitacaoDTO solicitacaoDTO);

	void alterarStatus(Long id, RespostaSolicitacaoDTO respostaDTO);

}

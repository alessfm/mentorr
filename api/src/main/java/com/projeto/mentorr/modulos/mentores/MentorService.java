package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface MentorService {

	ListaPaginacaoDTO buscarMentores(String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina);
	
	MentorDTO buscarPorApelido(String apelido);
	
	Mentor buscarPorId(Long idMentor);
	
	MentorDTO buscarMentorLogado();
	
	Mentor salvar(CadastroMentorDTO DTO);
	
	Mentor atualizar(CadastroMentorDTO DTO);
	
	Mentor atualizarPorId(Long idMentor, CadastroMentorDTO DTO);
	
	void alterarStatus();
	
	void alterarStatusPorId(Long idMentor);
	
	Long buscarTotalMentores();

}

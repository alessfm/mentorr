package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface MentorService {
	
	TotaisMentoresDTO buscarTotais();

	ListaPaginacaoDTO buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina);
	
	List<MentorDTO> buscarRecomendados();
	
	MentorDTO buscarPorApelido(String apelido);
	
	Mentor buscarPorId(Long idMentor);
	
	MentorDTO buscarMentorLogado();
	
	Mentor salvar(CadastroMentorDTO DTO);
	
	Mentor atualizar(CadastroMentorDTO DTO);
	
	Mentor atualizarPorId(Long idMentor, CadastroMentorDTO DTO);
	
	void alterarStatus();
	
	void alterarStatusPorId(Long idMentor);

}

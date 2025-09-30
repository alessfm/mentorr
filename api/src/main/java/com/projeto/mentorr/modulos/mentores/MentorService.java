package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface MentorService {

	TotaisMentoresDTO buscarTotais();

	ListaPaginacaoDTO<MentorDTO> buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina);

	List<MentorDTO> buscarMentoresDestaque();

	Mentor buscarPorId(Long id);

	Mentor buscarMentorLogado();

	MentorDTO buscarPerfilMentor(String apelido);

	Mentor salvar(CadastroMentorDTO mentorDTO);

	Mentor atualizar(Long id, CadastroMentorDTO mentorDTO);

	Mentor atualizarMentorLogado(CadastroMentorDTO mentorDTO);

	void ativarDesativar(Long id);

	void ativarDesativarMentorLogado();

}

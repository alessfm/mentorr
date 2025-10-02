package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface MentorService {

	TotaisMentoresDTO buscarTotais();

	ListaPaginacaoDTO<MentorDTO> buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina);

	List<MentorDTO> buscarMentoresDestaque();

	List<MentorDTO> buscarSimilaresMentor(String apelido, String cargo, String empresa);

	Mentor buscarPorId(Long id);

	Mentor buscarPorApelido(String apelido);

	Mentor buscarMentorLogado();

	MentorDTO buscarPerfilMentor(String apelido);

	Mentor salvar(CadastroMentorDTO mentorDTO);

	Mentor atualizar(Long id, CadastroMentorDTO mentorDTO);

	Mentor atualizarMentorLogado(CadastroMentorDTO mentorDTO);

	void ativarDesativar(Long id);

	void ativarDesativarMentorLogado();

}

package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.modulos.mentorias.Mentoria;
import com.projeto.mentorr.modulos.mentorias.MentoriaService;
import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.modulos.usuarios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MensagemMentoriaServiceImpl implements MensagemMentoriaService {

	private final MentoriaService mentoriaService;
	private final UsuarioService usuarioService;

	private final MensagemMentoriaRepository mensagemRepository;

	@Override
	public List<MensagemMentoriaDTO> buscarMensagensMentoria(Long idMentoria) {
		return mensagemRepository.buscarMensagensMentoria(idMentoria);
	}

	@Override
	public void enviaMensagemMentoria(Long idMentoria, NovaMensagemDTO mensagemDTO) {
		Usuario usuario = usuarioService.buscarUsuarioLogado();
		Mentoria mentoria = mentoriaService.buscarPorId(idMentoria);

		MensagemMentoria mensagem = MensagemMentoria.builder()
				.corpo(mensagemDTO.getCorpo())
				.vista(false)
				.usuario(usuario)
				.mentoria(mentoria)
				.build();

		mensagemRepository.saveAndFlush(mensagem);
	}

}

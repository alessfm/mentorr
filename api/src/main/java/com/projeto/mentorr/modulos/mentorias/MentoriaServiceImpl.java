package com.projeto.mentorr.modulos.mentorias;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.BadRequestException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.mentores.MentorService;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentor;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorRepository;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorService;
import com.projeto.mentorr.modulos.mentorias.mensagens.MensagemMentoria;
import com.projeto.mentorr.modulos.mentorias.mensagens.MensagemMentoriaRepository;
import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.modulos.usuarios.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MentoriaServiceImpl implements MentoriaService {

	private final MensagemMentoriaRepository mensagemRepository;
	private final MentoriaRepository mentoriaRepository;
	private final PlanoMentorRepository planoMentorRepository;

	private final MentorService mentorService;
	private final PlanoMentorService planoMentorService;
	private final UsuarioService usuarioService;

	@Override
	public Mentoria buscarPorId(Long id) {
		return mentoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Mentoria não encontrada"));
	}

	@Override
	public List<MentoriaDTO> buscarMentoriasAluno() {
		Usuario aluno = usuarioService.buscarUsuarioLogado();
		List<MentoriaDTO> mentorias = mentoriaRepository.buscarMentoriasAluno(aluno.getId());

		for(MentoriaDTO mentoria: mentorias) {
			mentoria.setPlano(planoMentorRepository.buscarPlanoMentoria(mentoria.getId()));
		}

		return mentorias;
	}

	@Override
	public List<MentoriaDTO> buscarMentoriasMentor() {
		Mentor mentor = mentorService.buscarMentorLogado();
		List<MentoriaDTO> mentorias = mentoriaRepository.buscarMentoriasMentor(mentor.getId());

		for(MentoriaDTO mentoria: mentorias) {
			mentoria.setPlano(planoMentorRepository.buscarPlanoMentoria(mentoria.getId()));
		}

		return mentorias;
	}

	@Override
	public void solicitarMentoria(SolicitacaoDTO solicitacaoDTO) {
		Usuario aluno = usuarioService.buscarUsuarioLogado();
		Mentor mentor = mentorService.buscarPorApelido(solicitacaoDTO.getApelidoMentor());
		PlanoMentor plano = planoMentorService.buscarPorCodigo(mentor.getId(), solicitacaoDTO.getCodigoPlano());

		if (aluno.getApelido() == solicitacaoDTO.getApelidoMentor()) {
			throw new BadRequestException("🤔 Hmm... Que tal escolher outro mentor?");
		}

		Mentoria mentoria = Mentoria.builder()
				.status(StatusMentoria.SOLICITADA)
				.mentor(mentor)
				.plano(plano)
				.aluno(aluno)
				.build();

		mentoria = mentoriaRepository.saveAndFlush(mentoria);

		MensagemMentoria mensagem = MensagemMentoria.builder()
				.corpo(solicitacaoDTO.getCorpo())
				.vista(false)
				.usuario(aluno)
				.mentoria(mentoria)
				.build();

		mensagemRepository.saveAndFlush(mensagem);
	}

	@Override
	public void alterarStatus(Long id, RespostaSolicitacaoDTO respostaDTO) {
		Usuario mentor = usuarioService.buscarUsuarioLogado();

		Mentoria mentoria = buscarPorId(id);
		mentoria.setStatus(respostaDTO.getStatus());
		mentoria = mentoriaRepository.save(mentoria);

		MensagemMentoria mensagem = MensagemMentoria.builder()
				.corpo(respostaDTO.getCorpo())
				.vista(false)
				.usuario(mentor)
				.mentoria(mentoria)
				.build();

		mensagemRepository.saveAndFlush(mensagem);
	}

}

package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.mentores.MentorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = { @Lazy })
@Service
public class AvaliacaoMentorServiceImpl implements AvaliacaoMentorService {

	private final MentorService mentorService;
	private final AvaliacaoMentorRepository avaliacaoMentorRepository;

	@Override
	public List<AvaliacaoMentorDTO> buscarAvaliacoesMentor(Long idMentor) {
		return avaliacaoMentorRepository.buscarAvaliacoesMentor(idMentor);
	}

	@Override
	public List<AvaliacaoMentorDTO> buscarAvaliacoesMentorLogado() {
		Mentor mentor = mentorService.buscarMentorLogado();
		return buscarAvaliacoesMentor(mentor.getId());
	}

}

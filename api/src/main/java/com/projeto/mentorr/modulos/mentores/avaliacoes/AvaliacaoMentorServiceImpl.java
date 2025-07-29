package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvaliacaoMentorServiceImpl implements AvaliacaoMentorService {
	
	private final AvaliacaoMentorRepository avaliacaoRepository;
	
	@Override
	public List<AvaliacaoMentorDTO> buscarAvaliacoesPorMentor(Long idMentor){
		return avaliacaoRepository.buscarAvaliacoesPorMentor(idMentor);
	}
}
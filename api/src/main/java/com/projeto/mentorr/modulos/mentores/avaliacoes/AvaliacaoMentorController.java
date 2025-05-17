package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_GESTAO', 'ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mentores/{idMentor}/avaliacoes")
public class AvaliacaoMentorController {

	private final AvaliacaoMentorService avaliacaoService;
	
	@GetMapping
    public List<AvaliacaoMentorDTO> buscarAvaliacoesPorMentor(@PathVariable Long idMentor) {
        return avaliacaoService.buscarAvaliacoesPorMentor(idMentor);
    }

}

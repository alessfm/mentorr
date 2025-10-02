package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mentor/avaliacoes")
public class AvaliacaoMentorController {

	private final AvaliacaoMentorService avaliacaoMentorService;

	@GetMapping
    public List<AvaliacaoMentorDTO> buscarAvaliacoesMentorLogado() {
        return avaliacaoMentorService.buscarAvaliacoesMentorLogado();
    }

}

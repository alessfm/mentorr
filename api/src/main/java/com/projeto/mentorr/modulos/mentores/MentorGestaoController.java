package com.projeto.mentorr.modulos.mentores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gestao/mentores")
public class MentorGestaoController {
	
	private final MentorService mentorService;
	
	@GetMapping("/{idMentor}")
	public Mentor buscarPorId(@PathVariable Long idMentor) {	
		return mentorService.buscarPorId(idMentor);
	}

	@PutMapping("/{idMentor}")
	public Mentor atualizarPorId(@PathVariable Long idMentor, @RequestBody @Valid CadastroMentorDTO DTO) {
		return mentorService.atualizarPorId(idMentor, DTO);
	}
	
	@PutMapping("/{idMentor}/status")
	public void alterarStatusPorId(@PathVariable Long idMentor) {
		mentorService.alterarStatusPorId(idMentor);
	}
	
}

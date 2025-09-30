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

	@GetMapping("/{id}")
	public Mentor buscarPorId(@PathVariable Long id) {	
		return mentorService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public Mentor atualizar(@PathVariable Long id, @RequestBody @Valid CadastroMentorDTO mentorDTO) {
		return mentorService.atualizar(id, mentorDTO);
	}

	@PutMapping("/{id}/status")
	public void ativarDesativar(@PathVariable Long id) {
		mentorService.ativarDesativar(id);
	}

}

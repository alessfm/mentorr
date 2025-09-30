package com.projeto.mentorr.modulos.mentores;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mentor")
public class MentorController {

	private final MentorService mentorService;

	@GetMapping
	public Mentor buscarMentorLogado() {	
		return mentorService.buscarMentorLogado();
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PostMapping
	public Mentor salvar(@RequestBody @Valid CadastroMentorDTO mentorDTO) {
		return mentorService.salvar(mentorDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping
	public Mentor atualizarMentorLogado(@RequestBody @Valid CadastroMentorDTO mentorDTO) {
		return mentorService.atualizarMentorLogado(mentorDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/status")
	public void ativarDesativarMentorLogado() {
		mentorService.ativarDesativarMentorLogado();
	}

}

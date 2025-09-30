package com.projeto.mentorr.modulos.usuarios;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_GESTAO', 'ROLE_ALUNO', 'ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@GetMapping
	public Usuario buscarUsuarioLogado() {	
		return usuarioService.buscarUsuarioLogado();
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping
	public Usuario atualizarUsuarioLogado(@RequestBody @Valid EditarUsuarioDTO usuarioDTO) {
		return usuarioService.atualizarUsuarioLogado(usuarioDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/status")
	public void ativarDesativarUsuarioLogado() {
		usuarioService.ativarDesativarUsuarioLogado();
	}

}

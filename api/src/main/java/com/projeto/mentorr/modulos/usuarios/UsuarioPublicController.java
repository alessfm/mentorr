package com.projeto.mentorr.modulos.usuarios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/usuarios")
public class UsuarioPublicController {

	private final UsuarioService usuarioService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/aluno")
	public Usuario salvarAluno(@RequestBody @Valid CadastrarUsuarioDTO usuarioDTO) {
		return usuarioService.salvar(usuarioDTO, TipoUsuario.ALUNO);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/mentor")
	public Usuario salvarMentor(@RequestBody @Valid CadastrarUsuarioDTO usuarioDTO) {
		return usuarioService.salvar(usuarioDTO, TipoUsuario.MENTOR);
	}

}

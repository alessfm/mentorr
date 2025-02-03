package com.projeto.mentorr.modulos.usuarios;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gestao/usuarios")
public class UsuarioGestaoController {
	
	private final UsuarioService usuarioService;

	@GetMapping("/busca")
	public ListaPaginacaoDTO buscarUsuarios(
		@RequestParam(required = false) String nome,
		@RequestParam(required = false) String apelido,
		@RequestParam(required = false) TipoUsuario tipo,
		@RequestParam(required = false) Boolean ativo,
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "10") Integer totalPorPagina
	) {	
		return usuarioService.buscarUsuarios(nome, apelido, tipo, ativo, pagina, totalPorPagina);
	}
	
	@GetMapping("/{idUsuario}")
	public Usuario buscarPorId(@PathVariable Long idUsuario) {	
		return usuarioService.buscarPorId(idUsuario);
	}
	
	@PutMapping("/{idUsuario}")
	public Usuario atualizarPorId(@PathVariable Long idUsuario, @RequestBody @Valid EditarUsuarioDTO DTO) {	
		return usuarioService.atualizarPorId(idUsuario, DTO);
	}
	
	@PutMapping("/{idUsuario}/status")
	public void alterarStatus(@PathVariable Long idUsuario) {
		usuarioService.alterarStatus(idUsuario);
	}
	
}

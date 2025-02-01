package com.projeto.mentorr.modulos.usuarios;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
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
	
	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
	@GetMapping("/{idUsuario}")
	public Usuario buscarPorId(@PathVariable Long idUsuario) {	
		return usuarioService.buscarPorId(idUsuario);
	}

	@PreAuthorize("hasAnyRole('ROLE_GESTAO', 'ROLE_ALUNO', 'ROLE_MENTOR')")
	@GetMapping
	public UsuarioDTO buscarUsuarioLogado() {	
		return usuarioService.buscarUsuarioLogado();
	}

	@PostMapping
	public Usuario salvar(@RequestBody @Valid CadastrarUsuarioDTO DTO) {
		return usuarioService.salvar(DTO);
	}

	@PreAuthorize("hasAnyRole('ROLE_GESTAO', 'ROLE_ALUNO', 'ROLE_MENTOR')")
	@PutMapping
	public Usuario atualizar(@RequestBody @Valid EditarUsuarioDTO DTO) {
		return usuarioService.atualizar(DTO);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_MENTOR')")
	@DeleteMapping
	public void excluirRestaurar() {
		usuarioService.excluirRestaurar();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
	@PutMapping("/{idUsuario}/status")
	public void alterarStatus(@PathVariable Long idUsuario) {
		usuarioService.alterarStatus(idUsuario);
	}
	
}

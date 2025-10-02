package com.projeto.mentorr.modulos.usuarios;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@GetMapping
	public ListaPaginacaoDTO<UsuarioDTO> buscarUsuarios(
		@RequestParam(required = false) String nome,
		@RequestParam(required = false) String apelido,
		@RequestParam(required = false) TipoUsuario tipo,
		@RequestParam(required = false) Boolean ativo,
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "10") Integer totalPorPagina
	) {	
		return usuarioService.buscarUsuarios(nome, apelido, tipo, ativo, pagina, totalPorPagina);
	}

	@GetMapping("/{id}")
	public Usuario buscarPorId(@PathVariable Long id) {	
		return usuarioService.buscarPorId(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Usuario salvar(@RequestBody @Valid CadastrarUsuarioDTO usuarioDTO) {	
		return usuarioService.salvar(usuarioDTO, TipoUsuario.GESTAO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable Long id, @RequestBody @Valid EditarUsuarioDTO usuarioDTO) {	
		return usuarioService.atualizar(id, usuarioDTO);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		usuarioService.excluir(id);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{id}/status")
	public void ativarDesativar(@PathVariable Long id) {
		usuarioService.ativarDesativar(id);
	}

}

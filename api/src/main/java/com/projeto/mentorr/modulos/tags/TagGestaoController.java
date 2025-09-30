package com.projeto.mentorr.modulos.tags;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gestao/tags")
public class TagGestaoController {

	private final TagService tagService;

	@GetMapping("/{id}")
	public Tag buscarPorId(@PathVariable Long id) {
		return tagService.buscarPorId(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Tag salvar(@RequestBody @Valid CadastroTagDTO tagDTO) {
		return tagService.salvar(tagDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public Tag atualizar(@PathVariable Long id, @RequestBody @Valid CadastroTagDTO tagDTO) {
		return tagService.atualizar(id, tagDTO);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		tagService.excluir(id);
	}

}

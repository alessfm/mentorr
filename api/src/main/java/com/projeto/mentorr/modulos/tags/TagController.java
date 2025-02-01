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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {

	private final TagService tagService;
	
	@GetMapping("/busca")
    public ListaPaginacaoDTO buscarTags(
		@RequestParam(required = false) String nome,
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "10") Integer totalPorPagina
    ) {
        return tagService.buscarTags(nome, pagina, totalPorPagina);
    }
	
	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
	@GetMapping("/{idTag}")
	public Tag buscarPorId(@PathVariable Long idTag) {
		return tagService.buscarPorId(idTag);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Tag salvar(@RequestBody @Valid CadastroTagDTO DTO) {
		return tagService.salvar(DTO);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
	@PutMapping("/{idTag}")
	public Tag atualizar(@PathVariable Long idTag, @RequestBody @Valid CadastroTagDTO DTO) {
		return tagService.atualizar(idTag, DTO);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idTag}")
	public void excluir(@PathVariable Long idTag) {
		tagService.excluir(idTag);
	}

}

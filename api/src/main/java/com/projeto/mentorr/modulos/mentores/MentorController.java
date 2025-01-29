package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/mentores")
public class MentorController {
	
	private final MentorService mentorService;

	@GetMapping("/busca")
	public ListaPaginacaoDTO buscarMentores(
		@RequestParam(required = false) String cargo,
		@RequestParam(required = false) String empresa,
		@RequestParam(required = false) List<Long> tags,
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "10") Integer totalPorPagina
	) {	
		return mentorService.buscarMentores(cargo, empresa, tags, pagina, totalPorPagina);
	}

	@GetMapping("/{apelido}")
	public MentorDTO buscarPorApelido(@PathVariable String apelido) {	
		return mentorService.buscarPorApelido(apelido);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MENTOR')")
	@GetMapping("/logado")
	public Mentor buscarMentorLogado() {	
		return mentorService.buscarMentorLogado();
	}

	@PreAuthorize("hasAnyRole('ROLE_MENTOR')")
	@PostMapping
	public Mentor salvar(@RequestBody @Valid CadastroMentorDTO DTO) {
		return mentorService.salvar(DTO);
	}

	@PreAuthorize("hasAnyRole('ROLE_MENTOR')")
	@PutMapping
	public Mentor atualizar(@RequestBody @Valid CadastroMentorDTO DTO) {
		return mentorService.atualizar(DTO);
	}
	
}

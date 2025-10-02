package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;

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

@PreAuthorize("hasAnyRole('ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mentor/planos")
public class PlanoMentorController {

	private final PlanoMentorService planoMentorService;

	@GetMapping
    public List<PlanoMentorDTO> buscarPlanosMentorLogado() {
        return planoMentorService.buscarPlanosMentorLogado();
    }

	@GetMapping("/{idPlano}")
	public PlanoMentor buscarPorIdPorMentorLogado(@PathVariable Long idPlano) {
		return planoMentorService.buscarPorIdPorMentorLogado(idPlano);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PlanoMentor salvarPorMentorLogado(@RequestBody @Valid CadastroPlanoMentorDTO planoDTO) {
		return planoMentorService.salvarPorMentorLogado(planoDTO);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/lote")
	public void salvarLotePorMentorLogado(@RequestBody @Valid List<CadastroPlanoMentorDTO> planosDTO) {
		planoMentorService.salvarLotePorMentorLogado(planosDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{idPlano}")
	public PlanoMentor atualizarPorMentorLogado(@PathVariable Long idPlano, @RequestBody @Valid CadastroPlanoMentorDTO planoDTO) {
		return planoMentorService.atualizarPorMentorLogado(idPlano, planoDTO);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idPlano}")
	public void excluirPorMentorLogado(@PathVariable Long idPlano) {
		planoMentorService.excluirPorMentorLogado(idPlano);
	}

}

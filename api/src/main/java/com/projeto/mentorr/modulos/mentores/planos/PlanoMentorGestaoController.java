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

@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gestao/mentor/{idMentor}/planos")
public class PlanoMentorGestaoController {

	private final PlanoMentorService planoMentorService;

	@GetMapping
    public List<PlanoMentorDTO> buscarPlanosMentor(@PathVariable Long idMentor) {
        return planoMentorService.buscarPlanosMentor(idMentor);
    }

	@GetMapping("/{idPlano}")
	public PlanoMentor buscarPorId(@PathVariable Long idMentor, @PathVariable Long idPlano) {
		return planoMentorService.buscarPorId(idMentor, idPlano);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PlanoMentor salvar(@PathVariable Long idMentor, @RequestBody @Valid CadastroPlanoMentorDTO planoDTO) {
		return planoMentorService.salvar(idMentor, planoDTO);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/lote")
	public void salvarLote(@PathVariable Long idMentor, @RequestBody @Valid List<CadastroPlanoMentorDTO> planosDTO) {
		planoMentorService.salvarLote(idMentor, planosDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{idPlano}")
	public PlanoMentor atualizar(@PathVariable Long idMentor, @PathVariable Long idPlano, @RequestBody @Valid CadastroPlanoMentorDTO planoDTO) {
		return planoMentorService.atualizar(idMentor, idPlano, planoDTO);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idPlano}")
	public void excluir(@PathVariable Long idMentor, @PathVariable Long idPlano) {
		planoMentorService.excluir(idMentor, idPlano);
	}

}

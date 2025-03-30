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

@PreAuthorize("hasAnyRole('ROLE_GESTAO', 'ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mentores/{idMentor}/planos")
public class PlanoMentorController {

	private final PlanoMentorService planoMentorService;
	
	@GetMapping
    public List<PlanoMentorDTO> buscarPlanosPorMentor(@PathVariable Long idMentor) {
        return planoMentorService.buscarPlanosPorMentor(idMentor);
    }
	
	@GetMapping("/{idPlano}")
	public PlanoMentor buscarPorId(@PathVariable Long idMentor, @PathVariable Long idPlano) {
		return planoMentorService.buscarPorId(idMentor, idPlano);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PlanoMentor salvar(@PathVariable Long idMentor, @RequestBody @Valid CadastroPlanoMentorDTO DTO) {
		return planoMentorService.salvar(idMentor, DTO);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/lote")
	public void salvarLote(@PathVariable Long idMentor, @RequestBody @Valid List<CadastroPlanoMentorDTO> DTO) {
		planoMentorService.salvarLote(idMentor, DTO);
	}
	
	@PutMapping("/{idPlano}")
	public PlanoMentor atualizar(@PathVariable Long idMentor, @PathVariable Long idPlano, @RequestBody @Valid CadastroPlanoMentorDTO DTO) {
		return planoMentorService.atualizar(idMentor, idPlano, DTO);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idPlano}")
	public void excluir(@PathVariable Long idMentor, @PathVariable Long idPlano) {
		planoMentorService.excluir(idMentor, idPlano);
	}
	

}

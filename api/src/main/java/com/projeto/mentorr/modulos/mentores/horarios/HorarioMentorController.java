package com.projeto.mentorr.modulos.mentores.horarios;

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
@RequestMapping("/api/mentores/{idMentor}/horarios")
public class HorarioMentorController {

	private final HorarioMentorService HorarioMentorService;
	
	@GetMapping
    public List<HorarioMentorDTO> buscarHorariosPorMentor(@PathVariable Long idMentor) {
        return HorarioMentorService.buscarHorariosPorMentor(idMentor);
    }
	
	@GetMapping("/{idHorario}")
	public HorarioMentor buscarPorId(@PathVariable Long idMentor, @PathVariable Long idHorario) {
		return HorarioMentorService.buscarPorId(idMentor, idHorario);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public HorarioMentor salvar(@PathVariable Long idMentor, @RequestBody @Valid CadastroHorarioMentorDTO DTO) {
		return HorarioMentorService.salvar(idMentor, DTO);
	}
	
	@PutMapping("/{idHorario}")
	public HorarioMentor atualizar(@PathVariable Long idMentor, @PathVariable Long idHorario, @RequestBody @Valid CadastroHorarioMentorDTO DTO) {
		return HorarioMentorService.atualizar(idMentor, idHorario, DTO);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idHorario}")
	public void excluir(@PathVariable Long idMentor, @PathVariable Long idHorario) {
		HorarioMentorService.excluir(idMentor, idHorario);
	}
	

}

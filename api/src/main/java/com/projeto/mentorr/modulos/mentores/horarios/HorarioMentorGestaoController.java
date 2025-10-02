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

@PreAuthorize("hasAnyRole('ROLE_GESTAO')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gestao/mentor/{idMentor}/horarios")
public class HorarioMentorGestaoController {

	private final HorarioMentorService horarioMentorService;

	@GetMapping
    public List<HorarioMentorDTO> buscarHorariosMentor(@PathVariable Long idMentor) {
        return horarioMentorService.buscarHorariosMentor(idMentor);
    }

	@GetMapping("/{idHorario}")
	public HorarioMentor buscarPorId(@PathVariable Long idMentor, @PathVariable Long idHorario) {
		return horarioMentorService.buscarPorId(idMentor, idHorario);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public HorarioMentor salvar(@PathVariable Long idMentor, @RequestBody @Valid CadastroHorarioMentorDTO horarioDTO) {
		return horarioMentorService.salvar(idMentor, horarioDTO);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/lote")
	public void salvarLote(@PathVariable Long idMentor, @RequestBody @Valid List<CadastroHorarioMentorDTO> horariosDTO) {
		horarioMentorService.salvarLote(idMentor, horariosDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{idHorario}")
	public HorarioMentor atualizar(@PathVariable Long idMentor, @PathVariable Long idHorario, @RequestBody @Valid CadastroHorarioMentorDTO horarioDTO) {
		return horarioMentorService.atualizar(idMentor, idHorario, horarioDTO);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idHorario}")
	public void excluir(@PathVariable Long idMentor, @PathVariable Long idHorario) {
		horarioMentorService.excluir(idMentor, idHorario);
	}

}

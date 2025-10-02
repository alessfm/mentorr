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
@RequestMapping("/api/mentor/horarios")
public class HorarioMentorController {

	private final HorarioMentorService horarioMentorService;

	@GetMapping
    public List<HorarioMentorDTO> buscarHorariosMentorLogado() {
        return horarioMentorService.buscarHorariosMentorLogado();
    }

	@GetMapping("/{idHorario}")
	public HorarioMentor buscarPorIdPorMentorLogado(@PathVariable Long idHorario) {
		return horarioMentorService.buscarPorIdPorMentorLogado(idHorario);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public HorarioMentor salvarPorMentorLogado(@RequestBody @Valid CadastroHorarioMentorDTO horarioDTO) {
		return horarioMentorService.salvarPorMentorLogado(horarioDTO);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/lote")
	public void salvarLotePorMentorLogado( @RequestBody @Valid List<CadastroHorarioMentorDTO> horariosDTO) {
		horarioMentorService.salvarLotePorMentorLogado(horariosDTO);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{idHorario}")
	public HorarioMentor atualizarPorMentorLogado( @PathVariable Long idHorario, @RequestBody @Valid CadastroHorarioMentorDTO horarioDTO) {
		return horarioMentorService.atualizarPorMentorLogado(idHorario, horarioDTO);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idHorario}")
	public void excluirPorMentorLogado(@PathVariable Long idHorario) {
		horarioMentorService.excluirPorMentorLogado(idHorario);
	}

}

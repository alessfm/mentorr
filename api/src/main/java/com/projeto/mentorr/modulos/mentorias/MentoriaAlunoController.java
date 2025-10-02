package com.projeto.mentorr.modulos.mentorias;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_ALUNO')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/aluno/mentorias")
public class MentoriaAlunoController {

	private final MentoriaService mentoriaService;

	@GetMapping
    public List<MentoriaDTO> buscarMentoriasAluno() {
        return mentoriaService.buscarMentoriasAluno();
    }

	@PostMapping
    public void solicitarMentoria(@RequestBody @Valid SolicitacaoDTO solicitacaoDTO) {
        mentoriaService.solicitarMentoria(solicitacaoDTO);
    }

}

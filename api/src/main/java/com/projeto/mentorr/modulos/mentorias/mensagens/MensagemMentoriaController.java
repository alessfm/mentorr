package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAnyRole('ROLE_ALUNO', 'ROLE_MENTOR')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mentorias/{idMentoria}/mensagens")
public class MensagemMentoriaController {

	private final MensagemMentoriaService mensagemMentoriaService;

	@GetMapping
    public List<MensagemMentoriaDTO> buscarMensagensMentoria(@PathVariable Long idMentoria) {
        return mensagemMentoriaService.buscarMensagensMentoria(idMentoria);
    }

	@PostMapping
    public void enviaMensagemMentoria(@PathVariable Long idMentoria, @RequestBody @Valid NovaMensagemDTO mensagemDTO) {
        mensagemMentoriaService.enviaMensagemMentoria(idMentoria, mensagemDTO);
    }

}

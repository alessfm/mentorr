package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/public/mentores")
public class MentorPublicController {

	private final MentorService mentorService;

	@GetMapping("/totais")
	public TotaisMentoresDTO buscarTotais() {	
		return mentorService.buscarTotais();
	}

	@GetMapping
	public ListaPaginacaoDTO<MentorDTO> buscarMentores(
		@RequestParam(required = false) String texto,
		@RequestParam(required = false) String cargo,
		@RequestParam(required = false) String empresa,
		@RequestParam(required = false) List<Long> tags,
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "10") Integer totalPorPagina
	) {	
		return mentorService.buscarMentores(texto, cargo, empresa, tags, pagina, totalPorPagina);
	}

	@GetMapping("/destaques")
	public List<MentorDTO> buscarMentoresDestaque() {	
		return mentorService.buscarMentoresDestaque();
	}

	@GetMapping("/perfil/{apelido}")
	public MentorDTO buscarPerfilMentor(@PathVariable String apelido) {	
		return mentorService.buscarPerfilMentor(apelido);
	}

}

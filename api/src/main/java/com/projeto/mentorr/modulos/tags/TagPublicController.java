package com.projeto.mentorr.modulos.tags;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/tags")
public class TagPublicController {

	private final TagService tagService;

	@GetMapping
    public ListaPaginacaoDTO<Tag> buscarTags(
		@RequestParam(required = false) String nome,
        @RequestParam(defaultValue = "1") Integer pagina,
        @RequestParam(defaultValue = "10") Integer totalPorPagina
    ) {
        return tagService.buscarTags(nome, pagina, totalPorPagina);
    }

	@GetMapping("/destaques")
    public List<Tag> buscarTagsDestaque() {
        return tagService.buscarTagsDestaque();
    }

}

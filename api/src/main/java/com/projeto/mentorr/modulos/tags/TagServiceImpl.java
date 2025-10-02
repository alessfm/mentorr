package com.projeto.mentorr.modulos.tags;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.InternalErrorException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.util.ListaPaginacaoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;

	@Override
	public ListaPaginacaoDTO<Tag> buscarTags(String nome, Integer pagina, Integer totalPorPagina) {
		return tagRepository.buscarTags(nome, pagina, totalPorPagina);
	}

	@Override
	public List<Tag> buscarTagsDestaque() {
		return tagRepository.buscarTagsDestaque();
	}

	@Override
	public Tag buscarPorId(Long id) {
		return tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag não encontrada"));
	}

	@Override
	public Tag salvar(CadastroTagDTO tagDTO) {
		Tag tag = Tag.builder()
				.nome(tagDTO.getNome())
				.build();

		return tagRepository.saveAndFlush(tag);
	}

	@Override
	public Tag atualizar(Long id, CadastroTagDTO tagDTO) {
		Tag tag = buscarPorId(id);
		tag.setNome(tagDTO.getNome());
		return tagRepository.saveAndFlush(tag);
	}

	@Override
	public void excluir(Long id) {
		Tag tag = buscarPorId(id);

		try {
			tagRepository.delete(tag);
		} catch (Exception e) {
			throw new InternalErrorException("Não é possível excluir a tag, pois existem dados vinculados");
		}
	}

}

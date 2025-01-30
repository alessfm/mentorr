package com.projeto.mentorr.modulos.tags;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
	
	private final TagRepository tagRepository;
	
	@Override
	public Tag buscarPorId(Long id) {
		return tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag não encontrada"));
	}

}
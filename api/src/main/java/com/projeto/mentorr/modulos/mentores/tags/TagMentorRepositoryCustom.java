package com.projeto.mentorr.modulos.mentores.tags;

import java.util.List;

import com.projeto.mentorr.modulos.tags.Tag;

public interface TagMentorRepositoryCustom {

	List<Tag> buscarTagsMentor(Long idMentor);

	List<Tag> buscarTagsPorApelido(String apelido, Integer totalTags);

}

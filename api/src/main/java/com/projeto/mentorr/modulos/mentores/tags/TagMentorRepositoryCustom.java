package com.projeto.mentorr.modulos.mentores.tags;

import java.util.List;

import com.projeto.mentorr.modulos.tags.Tag;

public interface TagMentorRepositoryCustom {
	
	List<Tag> buscarTagsPorApelidoMentor(String apelido);

}
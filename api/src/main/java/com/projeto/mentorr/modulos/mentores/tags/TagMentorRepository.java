package com.projeto.mentorr.modulos.mentores.tags;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMentorRepository extends JpaRepository<TagMentor, Long>, TagMentorRepositoryCustom {

	List<TagMentor> findByMentor_Id(Long idMentor);
	
}

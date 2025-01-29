package com.projeto.mentorr.modulos.mentores;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long>, MentorRepositoryCustom {

	Mentor findByUsuario_Id(Long id);
	
//	Long countById();
	
}

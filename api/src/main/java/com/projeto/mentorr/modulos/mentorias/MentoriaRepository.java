package com.projeto.mentorr.modulos.mentorias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoriaRepository extends JpaRepository<Mentoria, Long>, MentoriaRepositoryCustom {

	Long countByStatus(StatusMentoria status);

}

package com.projeto.mentorr.modulos.mentores;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long>, MentorRepositoryCustom {

	Optional<Mentor> findFirstByUsuario_Apelido(String apelido);

	Long countByAtivoIsTrue();

}

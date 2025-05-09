package com.projeto.mentorr.modulos.mentores.planos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoMentorRepository extends JpaRepository<PlanoMentor, Long>, PlanoMentorRepositoryCustom {

	Optional<PlanoMentor> findByIdAndMentor_Id(Long idPlano, Long idMentor);

}

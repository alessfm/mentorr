package com.projeto.mentorr.modulos.mentores.avaliacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoMentorRepository extends JpaRepository<AvaliacaoMentor, Long>, AvaliacaoMentorRepositoryCustom {

}

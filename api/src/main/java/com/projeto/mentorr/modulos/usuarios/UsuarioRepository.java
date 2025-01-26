package com.projeto.mentorr.modulos.usuarios;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom {

	UserDetails findByApelido(String apelido);
	
	Optional<Usuario> findFirstByApelido(String apelido);

}

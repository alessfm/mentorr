package com.projeto.mentorr.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.mentorr.modulos.usuarios.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;

	public AuthService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails usuario = usuarioRepository.findByApelido(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuário não encontrado: '%s'", username));
		}
		
		return usuario;
	}

}

package com.projeto.mentorr.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.projeto.mentorr.modulos.usuarios.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TokenDTO {
	
	private String token;
	private TipoUsuario profile;
	private Collection<? extends GrantedAuthority> authorities;

	public String getToken() {
		return this.token;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
}

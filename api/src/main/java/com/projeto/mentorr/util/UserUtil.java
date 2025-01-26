package com.projeto.mentorr.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.projeto.mentorr.modulos.usuarios.Usuario;

public class UserUtil {
	
	private static Object retornarUsuarioLogado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getPrincipal();
	}
	
	public static String retornarApelidoUsuarioLogado() {
		Object principal = retornarUsuarioLogado();
		return ((Usuario) principal).getApelido();
	}

}

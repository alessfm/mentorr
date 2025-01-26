package com.projeto.mentorr.core.security;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.modulos.usuarios.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final TokenService tokenService;
	private final UsuarioRepository usuarioRepository;

	public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		logger.warn("IP: {} - Route: {}", request.getRemoteAddr(), request.getRequestURL());
		
		String token = recuperarToken(request);
		
		if (Objects.nonNull(token)) {
			String subject = null;
			
			try {
				subject = tokenService.getSubject(token);
			} catch (TokenExpiredException e) {
				token = tokenService.refreshToken(token);
				subject = tokenService.getSubject(token);
			}
			
			Usuario usuario = (Usuario) usuarioRepository.findByApelido(subject);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}

		filterChain.doFilter(request, response);
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		
		if (Objects.nonNull(authorizationHeader)) {
			return authorizationHeader.replace("Bearer ", "");
		}
		
		return null;
	}
	
}

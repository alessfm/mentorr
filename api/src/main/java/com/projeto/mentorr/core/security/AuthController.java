package com.projeto.mentorr.core.security;

import java.util.Objects;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.mentorr.core.exception.BadRequestException;
import com.projeto.mentorr.modulos.usuarios.Usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid AuthDTO authDTO) throws AuthenticationException {
		Authentication authentication = validateUser(authDTO.getApelido(), authDTO.getSenha());
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		if (!usuario.getAtivo()) {
			return ResponseEntity.badRequest().body(new BadRequestException("Esta conta está desativada"));
		}
		
		String tokenJWT = tokenService.gerarToken(usuario);
		return ResponseEntity.ok(new TokenDTO(tokenJWT, usuario.getTipo(), usuario.getAuthorities()));
	}

	private Authentication validateUser(String username, String password) throws AuthenticationException {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		try {
			return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (DisabledException e) {
			throw new AuthenticationException("Esta conta foi desabilitada", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Credenciais incorretas, verifique os dados e tente novamente");
		}
	}
	
}

package com.projeto.mentorr.core.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.projeto.mentorr.modulos.usuarios.Usuario;

@Service
public class TokenService {
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    
		    return JWT.create()
		        .withIssuer("API")
		        .withSubject(usuario.getApelido())
		        .withClaim("apelido", usuario.getApelido())
		        .withExpiresAt(gerarDataExpiracaoToken())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar o token do usuário. Por favor, tente novamente");
		}
	}
	
	public String getSubject(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					 .withIssuer("API")
					 .build()
					 .verify(token)
					 .getSubject();
		} catch (JWTCreationException exception){
			throw new RuntimeException("Token inválido");
		}
	}

	public String refreshToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);

			DecodedJWT jwt = JWT.decode(token);

			String apelido = jwt.getClaim("apelido").asString();

			return JWT.create()
					.withIssuer("API")
					.withSubject(apelido)
					.withClaim("apelido", apelido)
					.withExpiresAt(gerarDataExpiracaoToken())
					.sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao atualizar o token do usuário. Por favor, tente novamente");
		}
	}

	private Instant gerarDataExpiracaoToken() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}

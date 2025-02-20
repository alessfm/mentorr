package com.projeto.mentorr.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private final SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
	        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(ex -> 
	            ex.authenticationEntryPoint((request, response, authException) -> {
	            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autenticado no Mentorr");
	            })
//	            .accessDeniedHandler((request, response, authException) -> {
//	            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não tem permissões para acessar este serviço");
//	            })
			)
	        .authorizeHttpRequests(req -> {
	            req.requestMatchers(
            		"/login",
            		"/actuator/**",
            		"/actuator/health",
            		"/swagger-ui/**",
            		"/v3/api-docs/**",
            		"/api/mentores/busca",
            		"/api/mentores/totais",
            		"/api/mentores/recomendados",
            		"/api/mentores/apelido/**",
            		"/api/tags/busca",
            		"/api/tags/destaque"
        		).permitAll();
	            req.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll();
	            req.anyRequest().authenticated();
	        })
	        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
		    .build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

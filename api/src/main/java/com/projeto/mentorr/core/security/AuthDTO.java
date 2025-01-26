package com.projeto.mentorr.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthDTO {
	
	private String apelido;
	private String senha;

}

package com.projeto.mentorr.modulos.usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String apelido;
	private String email;
	private String senha;
	private TipoUsuario tipo;
	
	public UsuarioDTO(String nome, String apelido, String email) {
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
	}

}

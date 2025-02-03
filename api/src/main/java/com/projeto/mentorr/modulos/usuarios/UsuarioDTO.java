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
	private String tipo;
	
	public UsuarioDTO(Long id, String nome, String apelido, String email, TipoUsuario tipo) {
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.tipo = tipo.getDescricao();
	}

}

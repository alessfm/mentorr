package com.projeto.mentorr.modulos.usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ListaUsuariosDTO {

	private Long id;
	private String nome;
	private String apelido;
	private String email;
	private String tipo;
	
	public ListaUsuariosDTO(Long id, String nome, String apelido, String email, TipoUsuario tipo) {
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.tipo = tipo.getDescricao();
	}

}

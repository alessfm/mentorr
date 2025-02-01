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
	private Boolean ativo;
	private Boolean excluido;
	
	public ListaUsuariosDTO(Long id, String nome, String apelido, String email, TipoUsuario tipo, Boolean ativo, Boolean excluido) {
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.tipo = tipo.getDescricao();
		this.ativo = ativo;
		this.excluido = excluido;
	}

}

package com.projeto.mentorr.modulos.usuarios;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String apelido;
	private String email;

	private TipoUsuario tipo;
	private String descricaoTipo;

	private Boolean ativo;
	private LocalDateTime dataDesativacao;

	public UsuarioDTO(Long id, String nome, String apelido, String email, TipoUsuario tipo, Boolean ativo, LocalDateTime dataDesativacao) {
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;

		this.tipo = tipo;
		this.descricaoTipo = tipo.getDescricao();

		this.ativo = ativo;
		this.dataDesativacao = dataDesativacao;
	}

}

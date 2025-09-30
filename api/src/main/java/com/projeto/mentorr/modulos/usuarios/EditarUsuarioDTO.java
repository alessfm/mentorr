package com.projeto.mentorr.modulos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditarUsuarioDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@Email(message = "O e-mail informado deve ser válido")
	@NotBlank(message = "O e-mail é obrigatório")
	private String email;

	private String apelido;

	private String senha;

}

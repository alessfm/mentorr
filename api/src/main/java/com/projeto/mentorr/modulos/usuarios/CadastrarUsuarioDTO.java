package com.projeto.mentorr.modulos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CadastrarUsuarioDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotBlank(message = "O apelido é obrigatório")
	private String apelido;

	@Email(message = "O e-mail informado deve ser válido")
	@NotBlank(message = "O e-mail é obrigatório")
	private String email;

	@NotBlank(message = "A senha é obrigatória")
	private String senha;

}

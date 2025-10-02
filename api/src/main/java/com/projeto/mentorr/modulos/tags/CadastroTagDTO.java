package com.projeto.mentorr.modulos.tags;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroTagDTO {

	@NotNull(message = "O nome é obrigatório")
	private String nome;

}

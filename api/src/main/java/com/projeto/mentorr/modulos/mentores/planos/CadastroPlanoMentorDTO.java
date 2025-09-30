package com.projeto.mentorr.modulos.mentores.planos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroPlanoMentorDTO {

	@NotNull(message = "O valor é obrigatório")
	private Double valor;

}

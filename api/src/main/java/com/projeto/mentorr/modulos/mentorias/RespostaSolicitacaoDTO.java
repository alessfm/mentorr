package com.projeto.mentorr.modulos.mentorias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RespostaSolicitacaoDTO {

	@NotNull(message = "O status é obrigatório")
	private StatusMentoria status;

	@NotBlank(message = "A mensagem é obrigatória")
	private String corpo;

}

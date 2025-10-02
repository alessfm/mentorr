package com.projeto.mentorr.modulos.mentorias.mensagens;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NovaMensagemDTO {

	@NotBlank(message = "A mensagem é obrigatória")
	private String corpo;

}

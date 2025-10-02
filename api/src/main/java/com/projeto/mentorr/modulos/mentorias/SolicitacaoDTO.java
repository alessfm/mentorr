package com.projeto.mentorr.modulos.mentorias;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitacaoDTO {

	@NotBlank(message = "O mentor é obrigatório")
	private String apelidoMentor;

	@NotNull(message = "O plano é obrigatório")
	private UUID codigoPlano;

	@NotBlank(message = "A mensagem é obrigatória")
	private String corpo;

}

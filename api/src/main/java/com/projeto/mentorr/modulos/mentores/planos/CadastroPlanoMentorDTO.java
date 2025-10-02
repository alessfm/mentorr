package com.projeto.mentorr.modulos.mentores.planos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroPlanoMentorDTO {

	@NotNull(message = "O tipo é obrigatórip")
	private TipoPlano tipo;

	@NotNull(message = "O valor é obrigatório")
	private Double valor;

	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;

	@NotNull(message = "O tempo de resposta é obrigatório")
	private Long tempoResposta;

	private Long totalChamadas;

	private Long duracaoChamada;

}

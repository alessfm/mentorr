package com.projeto.mentorr.modulos.mentores.planos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CadastroPlanoMentorDTO {
	
	@NotNull(message = "O valor é obrigatório")
	private Double valor;

}
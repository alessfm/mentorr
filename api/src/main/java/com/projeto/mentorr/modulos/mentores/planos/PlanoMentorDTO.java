package com.projeto.mentorr.modulos.mentores.planos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PlanoMentorDTO {

	private Long id;
	private Double valor;
	
	public PlanoMentorDTO(Double valor) {
		this.valor = valor;
	}

}
package com.projeto.mentorr.modulos.tags;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CadastroTagDTO {
	
	@NotNull(message = "O nome é obrigatório")
	private String nome;

}
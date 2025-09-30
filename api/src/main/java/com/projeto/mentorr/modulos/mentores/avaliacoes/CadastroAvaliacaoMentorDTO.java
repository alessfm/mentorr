package com.projeto.mentorr.modulos.mentores.avaliacoes;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroAvaliacaoMentorDTO {

	@NotNull(message = "A nota é obrigatória")
	private Long nota;

	@NotNull(message = "O comentário é obrigatório")
	private String comentario;

}

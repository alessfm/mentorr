package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AvaliacaoMentorDTO {

	private Long id;
	private Long nota;
	private LocalDateTime data;
	private String comentario;
	private String aluno;
	
	public AvaliacaoMentorDTO(Long nota, LocalDateTime data, String comentario, String aluno) {
		this.nota = nota;
		this.data = data;
		this.comentario = comentario;
		this.aluno = aluno;
	}

}

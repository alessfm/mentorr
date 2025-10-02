package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AvaliacaoMentorDTO {

	private Long id;
	private String aluno;
	private Long nota;
	private LocalDateTime data;
	private String comentario;

	public AvaliacaoMentorDTO(String aluno, Long nota, LocalDateTime data, String comentario) {
		this.aluno = aluno;
		this.nota = nota;
		this.data = data;
		this.comentario = comentario;
	}

}

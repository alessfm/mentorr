package com.projeto.mentorr.modulos.mentorias;

import java.time.LocalDateTime;

import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MentoriaDTO {

	private Long id;
	private StatusMentoria status;
	private String descricaoStatus;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	private String nomeAluno;
	private String apelidoAluno;
	private String fotoAluno;

	private String nomeMentor;
	private String apelidoMentor;
	private String fotoMentor;

	private PlanoMentorDTO plano;

	public MentoriaDTO(
		Long id, StatusMentoria status, LocalDateTime dataInicio, LocalDateTime dataFim,
		String nomeAluno, String apelidoAluno
	) {
		this.id = id;
		this.status = status;
		this.descricaoStatus = status.getDescricao();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;

		this.nomeAluno = nomeAluno;
		this.apelidoAluno = apelidoAluno;
	}

	public MentoriaDTO(
		Long id, StatusMentoria status, LocalDateTime dataInicio, LocalDateTime dataFim,
		String nomeMentor, String apelidoMentor, String fotoMentor
	) {
		this.id = id;
		this.status = status;
		this.descricaoStatus = status.getDescricao();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;

		this.nomeMentor = nomeMentor;
		this.apelidoMentor = apelidoMentor;
		this.fotoMentor = fotoMentor;
	}

}

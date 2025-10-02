package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MensagemMentoriaDTO {

	private Long id;
	private LocalDateTime criadaEm;
	private String corpo;
	private Boolean vista;

	private String nomeUsuario;
	private String apelidoUsuario;
	private String fotoUsuario;

	public MensagemMentoriaDTO(
		LocalDateTime criadaEm, String corpo, Boolean vista,
		String nomeUsuario, String apelidoUsuario
	) {
		this.criadaEm = criadaEm;
		this.corpo = corpo;
		this.vista = vista;

		this.nomeUsuario = nomeUsuario;
		this.apelidoUsuario = apelidoUsuario;
	}

}

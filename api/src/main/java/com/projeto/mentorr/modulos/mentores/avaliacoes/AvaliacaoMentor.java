package com.projeto.mentorr.modulos.mentores.avaliacoes;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.usuarios.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "AVALIACOES_MENTOR")
public class AvaliacaoMentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DATA")
	private LocalDateTime data;

	@Column(name = "NOTA")
	private Long nota;

	@Column(name = "COMENTARIO", length = 1000)
	private String comentario;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENTOR", nullable = false)
	private Mentor mentor;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ALUNO", nullable = false)
	private Usuario aluno;

}

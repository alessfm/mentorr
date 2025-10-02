package com.projeto.mentorr.modulos.mentores.planos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.mentorr.modulos.mentores.Mentor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "PLANOS_MENTOR")
public class PlanoMentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoPlano tipo;

	@Column(name = "VALOR", nullable = false)
	private Double valor;

	@Column(name = "DESCRICAO", nullable = false, length = 150)
	private String descricao;

	@Column(name = "TOTAL_CHAMADAS")
	private Long totalChamadas;

	@Column(name = "DURACAO_CHAMADA")
	private Long duracaoChamada;

	@Column(name = "TEMPO_RESPOSTA", nullable = false)
	private Long tempoResposta;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENTOR", nullable = false)
	private Mentor mentor;

}

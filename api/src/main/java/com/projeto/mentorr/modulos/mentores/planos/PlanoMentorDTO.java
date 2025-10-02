package com.projeto.mentorr.modulos.mentores.planos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlanoMentorDTO {

	private Long id;
	private UUID codigo;
	private TipoPlano tipo;
	private String descricaoTipo;

	private Double valor;
	private String descricao;

	private Long totalChamadas;
	private Long duracaoChamada;
	private Long tempoResposta;

	/**
	 * @apiNote Construtor do buscarPlanosMentor().
	 */
	public PlanoMentorDTO(
		Long id, TipoPlano tipo, Double valor, String descricao, Long totalChamadas, Long duracaoChamada, Long tempoResposta
	) {
		this.id = id;
		this.tipo = tipo;
		this.descricaoTipo = tipo.getDescricao();

		this.valor = valor;
		this.descricao = descricao;

		this.totalChamadas = totalChamadas;
		this.duracaoChamada = duracaoChamada;
		this.tempoResposta = tempoResposta;
	}

	/**
	 * @apiNote Construtor do buscarPlanosMentorPublic().
	 */
	public PlanoMentorDTO(
		UUID codigo, TipoPlano tipo, Double valor, String descricao, Long totalChamadas, Long duracaoChamada, Long tempoResposta
	) {
		this.codigo =codigo;
		this.tipo = tipo;
		this.descricaoTipo = tipo.getDescricao();

		this.valor = valor;
		this.descricao = descricao;

		this.totalChamadas = totalChamadas;
		this.duracaoChamada = duracaoChamada;
		this.tempoResposta = tempoResposta;
	}

}

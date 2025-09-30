package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.List;

import com.projeto.mentorr.modulos.mentores.avaliacoes.AvaliacaoMentorDTO;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentorDTO;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorDTO;
import com.projeto.mentorr.modulos.tags.Tag;
import com.projeto.mentorr.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MentorDTO {

	private Long id;
	private Boolean ativo;

	private String nome;
	private String apelido;
	private String foto;
	private String descricao;

	private String cargo;
	private String empresa;
	private LocalDate dataInicio;

	private List<Tag> tags;
	private List<PlanoMentorDTO> planos;
	private List<HorarioMentorDTO> horarios;

	private Float nota;
	private List<AvaliacaoMentorDTO> avaliacoes;

	/**
	 * @apiNote Construtor do buscarMentores().
	 */
	public MentorDTO(
		String nome, String apelido, String foto, String descricao, 
		String cargo, String empresa, LocalDate dataInicio, Float nota
	) {
		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.descricao = Util.resumirTexto(descricao);

		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;

//		this.plano = plano;
		this.nota = nota;
	}

	/**
	 * @apiNote Construtor do buscarMentoresDestaque().
	 */
	public MentorDTO(String nome, String apelido, String foto, String cargo, Float nota) {
		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.cargo = cargo;
		this.nota = nota;
	}

	/**
	 * @apiNote Construtor do buscarPorApelido().
	 */
	public MentorDTO(
		Long id, String nome, String apelido, String foto, String descricao, 
		String cargo, String empresa, LocalDate dataInicio, Float nota
	) {
		this.id = id;

		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.descricao = descricao;

		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;

		this.nota = nota;
	}

}

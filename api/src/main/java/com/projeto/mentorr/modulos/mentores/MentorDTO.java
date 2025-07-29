package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.List;

import com.projeto.mentorr.modulos.mentores.avaliacoes.AvaliacaoMentorDTO;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentorDTO;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorDTO;
import com.projeto.mentorr.modulos.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class MentorDTO {

	private Long id;
	private String nome;
	private String apelido;
	private String foto;
	private String descricao;

	private String cargo;
	private String empresa;
	private LocalDate dataInicio;
	
	private Boolean ativo;

	private List<Tag> tags;
	private List<PlanoMentorDTO> planos;
	private List<HorarioMentorDTO> horarios;

	private Float nota;
	private List<AvaliacaoMentorDTO> avaliacoes;
	
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
	 * @apiNote Construtor do buscarMentorLogado().
	 */
	public MentorDTO(
		Long id, String foto, String descricao, String cargo, String empresa, 
		LocalDate dataInicio, Float nota, Boolean ativo
	) {
		this.id = id;
		this.foto = foto;
		this.descricao = descricao;
		
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		
		this.nota = nota;
		this.ativo = ativo;
	}
	
	/**
	 * @apiNote Construtor do buscarPorApelido().
	 */
	public MentorDTO(
		String nome, String apelido, String foto, String descricao, 
		String cargo, String empresa, LocalDate dataInicio, Float nota, Boolean ativo
	) {
		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.descricao = descricao;
		
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		
		this.nota = nota;
		this.ativo = ativo;
	}

}

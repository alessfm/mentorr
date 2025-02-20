package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.List;

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
	private List<PlanoMentorDTO> planos;
	private List<HorarioMentorDTO> horarios;
	private List<Tag> tags;
	
	/**
	 * @apiNote Construtor para ser utilizado na listagem de mentores recomendados.
	 */
	public MentorDTO(String nome, String apelido, String foto, String cargo) {
		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.cargo = cargo;
	}
	
	/**
	 * @apiNote Construtor para ser utilizado na página do mentor.
	 */
	public MentorDTO(Long id, String foto, String descricao, String cargo, String empresa, LocalDate dataInicio, Boolean ativo) {
		this.id = id;
		this.foto = foto;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		this.ativo = ativo;
	}
	
	/**
	 * @apiNote Construtor para ser utilizado na busca de mentores.
	 */
	public MentorDTO(String nome, String apelido, String foto, String descricao, String cargo, String empresa, LocalDate dataInicio, Boolean ativo) {
		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		this.ativo = ativo;
	}

}

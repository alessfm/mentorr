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
	private String descricao;
	private String cargo;
	private String empresa;
	private LocalDate dataInicio;
	private List<PlanoMentorDTO> planos;
	private List<HorarioMentorDTO> horarios;
	private List<Tag> tags;
	
	public MentorDTO(Long id, String descricao, String cargo, String empresa, LocalDate dataInicio) {
		this.id = id;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
	}
	
	public MentorDTO(String nome, String apelido, String cargo, String empresa) {
		this.nome = nome;
		this.apelido = apelido;
		this.cargo = cargo;
		this.empresa = empresa;
	}
	
	public MentorDTO(String nome, String apelido, String descricao, String cargo, String empresa, LocalDate dataInicio) {
		this.nome = nome;
		this.apelido = apelido;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
	}
	
	public MentorDTO(
		String nome, String apelido, String descricao, String cargo, String empresa, 
		LocalDate dataInicio, List<PlanoMentorDTO> planos, List<HorarioMentorDTO> horarios, List<Tag> tags
	) {
		this.nome = nome;
		this.apelido = apelido;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		this.planos = planos;
		this.horarios = horarios;
		this.tags = tags;
	}

}

package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ListaMentoresDTO {

	private Long id;
	private String nome;
	private String apelido;
	private String descricao;
	private String cargo;
	private String empresa;
	private LocalDate dataInicio;
//	private List<Tags> tags;
	
	public ListaMentoresDTO(String nome, String apelido, String descricao, String cargo, String empresa, LocalDate dataInicio/* , List<Tags> */) {
		this.nome = nome;
		this.apelido = apelido;
		this.descricao = descricao;
		this.cargo = cargo;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
//		this.tags = tags;
//		this.plano = plano;
	}
	
	public ListaMentoresDTO(String nome, String apelido, String cargo, String empresa /* , List<Tags> */) {
		this.nome = nome;
		this.apelido = apelido;
		this.cargo = cargo;
		this.empresa = empresa;
//		this.tags = tags;
	}

}

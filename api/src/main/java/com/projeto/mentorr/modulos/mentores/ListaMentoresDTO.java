package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.List;

import com.projeto.mentorr.modulos.tags.Tag;
import com.projeto.mentorr.util.Util;

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
	private String foto;
	private String descricao;
	
	private String cargo;
	private String empresa;
	private LocalDate dataInicio;
	
	private List<Tag> tags;
//	private PlanoMentor plano;
	private Float nota;
	
	/**
	 * @apiNote Construtor do buscarMentores().
	 */
	public ListaMentoresDTO(
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

}

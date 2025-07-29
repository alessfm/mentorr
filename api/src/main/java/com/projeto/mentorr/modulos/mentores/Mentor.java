package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentor;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentor;
import com.projeto.mentorr.modulos.usuarios.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "mentores")
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FOTO_URL", nullable = false)
	private String foto;

	@Column(name = "DESCRICAO", nullable = false, length = 1000)
	private String descricao;
	
	@Column(name = "CARGO", nullable = false)
	private String cargo;

	@Column(name = "EMPRESA", nullable = false)
	private String empresa;
	
	@Column(name = "DATA_INICIO", nullable = false)
	private LocalDate dataInicio;
	
	@Column(name = "FLAG_ATIVO", columnDefinition = "boolean default false", nullable = false)
	private Boolean ativo;
	
	@Column(name = "NOTA")
	private Float nota;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", unique = true)
	private Usuario usuario;
	
	@JsonIgnore
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
	private List<PlanoMentor> planos;
	
	@JsonIgnore
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
	private List<HorarioMentor> horarios;

}

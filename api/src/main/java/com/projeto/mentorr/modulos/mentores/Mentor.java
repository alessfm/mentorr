package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentor;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentor;
import com.projeto.mentorr.modulos.mentores.tags.Tag;
import com.projeto.mentorr.modulos.usuarios.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@Column(name = "CARGO", nullable = false)
	private String cargo;

	@Column(name = "EMPRESA", nullable = false)
	private String empresa;
	
	@Column(name = "DATA_INICIO", nullable = false)
	private LocalDate dataInicio;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", unique = true)
	private Usuario usuario;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
	private List<PlanoMentor> planos;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
	private List<HorarioMentor> horarios;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tags_mentor", joinColumns = @JoinColumn(name = "id_mentor", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_tag", referencedColumnName = "id"))
	private Set<Tag> tags;

	public Collection<?> getTags() {
		return tags.stream().map(tag -> tag.getNome()).collect(Collectors.toList());
	}

}

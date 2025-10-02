package com.projeto.mentorr.modulos.mentorias;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentor;
import com.projeto.mentorr.modulos.mentorias.mensagens.MensagemMentoria;
import com.projeto.mentorr.modulos.usuarios.Usuario;

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
import jakarta.persistence.OneToMany;
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
@Table(name = "MENTORIAS")
public class Mentoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private StatusMentoria status;

	@CreationTimestamp
	@Column(name = "DATA_INICIO")
	private LocalDateTime dataInicio;

	@Column(name = "DATA_FIM")
	private LocalDateTime dataFim;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENTOR", nullable = false)
	private Mentor mentor;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PLANO", nullable = false)
	private PlanoMentor plano;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ALUNO", nullable = false)
	private Usuario aluno;

	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "mentoria", fetch = FetchType.EAGER)
	private List<MensagemMentoria> mensagens;

}

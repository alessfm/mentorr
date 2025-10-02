package com.projeto.mentorr.modulos.mentorias.mensagens;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.mentorr.modulos.mentorias.Mentoria;
import com.projeto.mentorr.modulos.usuarios.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "MENSAGENS_MENTORIA")
public class MensagemMentoria {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name = "CRIADA_EM", nullable = false)
	private LocalDateTime criadaEm;

	@Column(length = 1000, nullable = false)
	private String corpo;

	@Column(name = "FLAG_VISTA", columnDefinition = "boolean default false", nullable = false)
	private Boolean vista;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENTORIA", nullable = false)
	private Mentoria mentoria;

}

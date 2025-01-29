package com.projeto.mentorr.modulos.mentores.horarios;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto.mentorr.modulos.mentores.Mentor;

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
@Table(name = "HORARIOS_MENTOR")
public class HorarioMentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "DIA", nullable = false)
	private DayOfWeek dia;

	@Column(name = "HORA_INICIO", nullable = false)
	private LocalTime horaInicio;

	@Column(name = "HORA_FIM", nullable = false)
	private LocalTime horaFim;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MENTOR", nullable = false)
	private Mentor mentor;

}
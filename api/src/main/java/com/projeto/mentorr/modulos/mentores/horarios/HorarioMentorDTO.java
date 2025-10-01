package com.projeto.mentorr.modulos.mentores.horarios;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.projeto.mentorr.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HorarioMentorDTO {

	private Long id;
	private DayOfWeek dia;
	private String descricaoDia;
	private LocalTime horaInicio;
	private LocalTime horaFim;

	/**
	 * @apiNote Construtor do buscarHorariosMentor().
	 */
	public HorarioMentorDTO(Long id, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFim) {
		this.id = id;
		this.dia = dia;
		this.descricaoDia = Util.retornarDiaEnumFormatado(dia);
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	/**
	 * @apiNote Construtor do buscarHorariosMentorPublic().
	 */
	public HorarioMentorDTO(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFim) {
		this.dia = dia;
		this.descricaoDia = Util.retornarDiaEnumFormatado(dia);
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

}

package com.projeto.mentorr.modulos.mentores.horarios;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.projeto.mentorr.util.Util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class HorarioMentorDTO {

	private Long id;
	private String dia;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	
	public HorarioMentorDTO(Long id, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFim) {
		this.id = id;
		this.dia = Util.retornarDiaEnumFormatado(dia);
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
	
	public HorarioMentorDTO(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFim) {
		this.dia = Util.retornarDiaEnumFormatado(dia);
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

}
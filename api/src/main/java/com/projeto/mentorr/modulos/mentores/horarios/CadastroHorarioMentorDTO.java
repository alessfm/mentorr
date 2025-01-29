package com.projeto.mentorr.modulos.mentores.horarios;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CadastroHorarioMentorDTO {

	@NotNull(message = "O dia de funcionamento é obrigatório")
	private DayOfWeek dia;
	
	@NotNull(message = "O horário inicial é obrigatório")
	private LocalTime horaInicio;
	
	@NotNull(message = "O horário final é obrigatório")
	private LocalTime horaFim;

}
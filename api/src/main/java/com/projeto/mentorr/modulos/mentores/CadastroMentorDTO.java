package com.projeto.mentorr.modulos.mentores;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroMentorDTO {

	@NotBlank(message = "A foto é obrigatória")
	private String foto;

	@NotBlank(message = "A descrição(bio) é obrigatória")
	private String descricao;

	@NotBlank(message = "O cargo é obrigatório")
	private String cargo;

	@NotBlank(message = "O nome da empresa é obrigatório")
	private String empresa;

	@NotNull(message = "A data inicial na empresa é obrigatória")
	private LocalDate dataInicio;

	@NotEmpty(message = "As tags são obrigatórias")
	private List<Long> tags;

}

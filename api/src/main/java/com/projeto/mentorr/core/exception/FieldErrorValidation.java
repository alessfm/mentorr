package com.projeto.mentorr.core.exception;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class FieldErrorValidation {

	private String campo, mensagem;
	
	public FieldErrorValidation(FieldError erro) {
		this.campo = erro.getField();
		this.mensagem = erro.getDefaultMessage();
	}

}

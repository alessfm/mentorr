package com.projeto.mentorr.core.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
		return ResponseHandler.generateResponse("Não foi possível salvar as informações, por favor tente novamente", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		return ResponseHandler.generateResponse("Não foi possível salvar as informações, por favor tente novamente. Motivo: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleForbiddenException(AccessDeniedException e) {
		return ResponseHandler.generateResponse("Você não tem permissões para acessar este serviço", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidArgumentsException(MethodArgumentNotValidException e) {
		List<FieldError> errors = e.getFieldErrors();
		return ResponseEntity.badRequest().body(errors.stream().map(FieldErrorValidation::new).collect(Collectors.toList()));
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<?> handleUnauthorizedRequest(AuthenticationException e) {
		return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
		return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
		return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleInternalException(Exception e) {
		return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
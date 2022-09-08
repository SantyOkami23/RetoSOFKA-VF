package com.pruebas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.pruebas.dto.ErrorDTO;

//@RestControllerAdvice
public class CAdvice 
{
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex)
	{
		ErrorDTO error = ErrorDTO.builder().code("P-500").message(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}

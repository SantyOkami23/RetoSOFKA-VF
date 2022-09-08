package com.pruebas.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdivice 
{
	
    private String applicationName;

    @ExceptionHandler(PersonNotFoundException.class)
    public HttpEntity<ErrorModel> PersonaNotFoundException(PersonNotFoundException error) 
    { 
    	return buildError(HttpStatus.NOT_FOUND, error.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public HttpEntity<ErrorModel> exception(Exception error) 
    {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, error.getMessage());
    }

    private ResponseEntity<ErrorModel> buildError(HttpStatus status, String message) 
    {
        return ResponseEntity
                .status(status).body(new ErrorModel(applicationName, status.value(), message));
    }

}

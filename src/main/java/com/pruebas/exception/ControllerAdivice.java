package com.pruebas.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


@ControllerAdvice
public class ControllerAdivice 
{
	
    private String applicationName;

    @ExceptionHandler(PersonNotFoundException.class)
    public HttpEntity<ErrorModel> PersonaNotFoundException(PersonNotFoundException error) 
    { 
    	return buildError(HttpStatus.NOT_FOUND, error.getMessage());
    }
    
    @ExceptionHandler(NoPeopleFound.class)
    public HttpEntity<ErrorModel> NoPeopleFind(NoPeopleFound error) 
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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        // get spring errors
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // convert errors to standard string
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() +  " "));

        // return error info object with standard json
        ErrorModel errorModel = new ErrorModel(errorMessage.toString(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

}




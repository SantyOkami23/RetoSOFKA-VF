package com.pruebas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShipNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ShipNotFoundException(String message) {
		super(message);
	}
}

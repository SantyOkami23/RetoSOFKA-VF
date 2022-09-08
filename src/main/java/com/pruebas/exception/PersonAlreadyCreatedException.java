package com.pruebas.exception;

@SuppressWarnings("serial")
public class PersonAlreadyCreatedException extends RuntimeException{
    public PersonAlreadyCreatedException() {
    }

    public PersonAlreadyCreatedException(String message) {
        super(message);
    }
}

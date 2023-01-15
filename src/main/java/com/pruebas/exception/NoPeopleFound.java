package com.pruebas.exception;

@SuppressWarnings("serial")
public class NoPeopleFound extends RuntimeException{
    public NoPeopleFound() {
    }

    public NoPeopleFound(String message) {
        super(message);
    }
}

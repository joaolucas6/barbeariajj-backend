package com.joaolucas.barbeariajj.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}

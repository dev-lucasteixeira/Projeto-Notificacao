package com.lucasteixeira.notificacao.infrastructure.Exceptions;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}

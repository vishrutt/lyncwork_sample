package com.example.demo.customExceptions;

public class NameNotFoundException extends RuntimeException{
    public NameNotFoundException(final String message) {
        super(message);
    }
}

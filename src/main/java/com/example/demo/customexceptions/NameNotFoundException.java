package com.example.demo.customexceptions;

public class NameNotFoundException extends RuntimeException{
    public NameNotFoundException(final String message) {
        super(message);
    }
}

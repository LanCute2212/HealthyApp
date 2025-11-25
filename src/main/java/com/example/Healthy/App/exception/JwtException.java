package com.example.Healthy.App.exception;

public class JwtException extends RuntimeException{
    public JwtException(String message) {
        super(message);
    }
}
package com.sunflower.onlinetest.service.exception;

public class UnauthorizedException extends RuntimeException {

    private String message;

    public UnauthorizedException(String message) {
        this.message = message;
    }
}

package com.hackerrank.tradingplatform.exception;

public class TraderAlreadyExistsException extends RuntimeException {
    public TraderAlreadyExistsException(String message) {
        super(message);
    }
}

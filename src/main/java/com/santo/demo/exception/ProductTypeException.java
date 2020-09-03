package com.santo.demo.exception;

public class ProductTypeException extends RuntimeException {
    public ProductTypeException(String message) {
        super(message);
    }

    public ProductTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

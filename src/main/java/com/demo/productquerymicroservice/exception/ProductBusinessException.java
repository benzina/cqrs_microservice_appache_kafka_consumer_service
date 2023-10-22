package com.demo.productquerymicroservice.exception;

public class ProductBusinessException extends RuntimeException {
    public ProductBusinessException(String message, Long id) {
        super(message + " " + id);
    }
}

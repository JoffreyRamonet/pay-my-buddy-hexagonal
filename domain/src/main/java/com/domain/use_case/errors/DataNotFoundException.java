package com.domain.use_case.errors;

public class DataNotFoundException extends RuntimeException {
    
    public DataNotFoundException(String message) {
        super(message);
    }
}

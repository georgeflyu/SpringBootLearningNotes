package com.learning.config;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{

    public String message;

    public Throwable throwable;

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

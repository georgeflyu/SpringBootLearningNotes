package com.learning.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

//@Log
//@ControllerAdvice
//@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Exception exceptionHandler(Exception e, HttpServletRequest httpServletRequest) {

        return null;
    }
}

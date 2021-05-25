package com.sula.productmanagementsystem.exception.advice;

import com.sula.productmanagementsystem.exception.ResourceAlreadyExisistException;
import com.sula.productmanagementsystem.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException e) {

        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExisistException.class)
    public ResponseEntity handleException(ResourceAlreadyExisistException e) {

        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}

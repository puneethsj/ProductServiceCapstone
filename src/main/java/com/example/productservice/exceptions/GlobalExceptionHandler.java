package com.example.productservice.exceptions;

import com.example.productservice.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerExceptions() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage("Null pointer exception occurred");
        return  errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Failure");
        errorDto.setMessage(productNotFoundException.getMessage());

        return errorDto;
    }
}

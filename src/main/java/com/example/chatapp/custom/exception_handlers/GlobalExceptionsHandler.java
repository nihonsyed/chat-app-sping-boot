package com.example.chatapp.custom.exception_handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleRestOfTheExceptions(Exception exception, WebRequest request)
    {
        log.error("Error: {}",exception);
        return handleExceptionInternal(exception,"Internal Server Problem!", HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR,request);
    }
    //todo:all
}

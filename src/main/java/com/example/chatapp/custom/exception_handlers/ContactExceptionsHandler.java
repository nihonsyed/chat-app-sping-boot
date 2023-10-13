package com.example.chatapp.custom.exception_handlers;

import com.example.chatapp.controllers.contact.ContactController;
import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.NoContactFound;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


//todo:error responsebody
@ControllerAdvice(assignableTypes = ContactController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContactExceptionsHandler extends GlobalExceptionsHandler {

    @ExceptionHandler(value = NoContactFound.class)
    public ResponseEntity<Object> handleNoContactFoundException(NoContactFound exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = ContactNotFound.class)
    public ResponseEntity<Object> handleContactNotFoundException(ContactNotFound exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }
}
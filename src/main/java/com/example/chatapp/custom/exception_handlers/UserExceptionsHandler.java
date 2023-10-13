package com.example.chatapp.custom.exception_handlers;

import com.example.chatapp.controllers.user.UserController;
import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(assignableTypes = UserController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionsHandler extends GlobalExceptionsHandler {

    @ExceptionHandler(value = NoUserFoundException.class)
    public ResponseEntity<Object> handleNoUserFoundException(NoUserFoundException exception, WebRequest request)
    {
        //todo:implement error response body
        return handleExceptionInternal(exception,exception.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND,request);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest request)
    {
        //todo:implement error response body
        return handleExceptionInternal(exception,exception.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND,request);
    }
}

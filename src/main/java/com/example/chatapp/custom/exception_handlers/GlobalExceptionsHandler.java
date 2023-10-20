package com.example.chatapp.custom.exception_handlers;

import com.example.chatapp.custom.api.response.ErrorResponseBody;
import com.example.chatapp.custom.exceptions.CustomException;
import com.example.chatapp.enums.utils.LogMessages;
import jakarta.servlet.http.HttpServletRequest;
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

import java.util.Date;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleRestOfTheExceptions(Exception exception, WebRequest request)
    {
        log.error(LogMessages.EXCEPTION_OCCURRED.getDescription(),exception);
        return handleExceptionInternal(exception,"Internal Server Problem!", HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException exception, WebRequest webRequest, HttpServletRequest httpServletRequest)
    {
        log.error(LogMessages.EXCEPTION_OCCURRED.getDescription(),exception);
        ErrorResponseBody body=new ErrorResponseBody(     exception.getStatusCode(),
                                                          exception.getHttpStatus().name(),
                                                          exception.getMessage(),
                                                          httpServletRequest.getRequestURI(),
                                                          new Date()
                                                       );
        //todo:set header
       return handleExceptionInternal(exception,body,HttpHeaders.EMPTY,exception.getHttpStatus(),webRequest);
    }

}

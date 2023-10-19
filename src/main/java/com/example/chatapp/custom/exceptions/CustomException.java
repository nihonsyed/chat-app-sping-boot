package com.example.chatapp.custom.exceptions;


import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{



    final int statusCode;

    private final HttpStatus httpStatus;





    public CustomException(int statusCode, HttpStatus httpStatus) {
        this.statusCode = statusCode;

        this.httpStatus = httpStatus;
    }

    public CustomException(String message, int statusCode, HttpStatus httpStatus) {
        super(message);
        this.statusCode = statusCode;

        this.httpStatus = httpStatus;
    }

    public CustomException(String message, Throwable cause, int statusCode, HttpStatus httpStatus) {
        super(message, cause);
        this.statusCode = statusCode;

        this.httpStatus = httpStatus;
    }

    public CustomException(Throwable cause, int statusCode, HttpStatus httpStatus) {
        super(cause);
        this.statusCode = statusCode;

        this.httpStatus = httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

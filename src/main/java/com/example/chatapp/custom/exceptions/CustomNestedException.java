package com.example.chatapp.custom.exceptions;

import org.springframework.http.HttpStatus;

public class CustomNestedException extends CustomException {


    private final Exception rootException;

    public CustomNestedException(String message, Exception rootException,int statusCode, HttpStatus httpStatus) {
        super(message, rootException.getCause(), statusCode, httpStatus);
        this.rootException = rootException;
    }

    public Exception getRootException() {
        return rootException;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return rootException.getStackTrace();
    }

    @Override
    public void printStackTrace() {
        rootException.printStackTrace();
    }


}

package com.example.chatapp.custom.exceptions;

public class CustomException extends RuntimeException{


    private String message;

    private Throwable cause;
    public CustomException() {

    }

    public CustomException(String message) {
        super(message);
        this.message=message;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.message=message;
        this.cause=cause;
    }

    public CustomException(Throwable cause) {
        super(cause);
        this.cause=cause;
    }

}

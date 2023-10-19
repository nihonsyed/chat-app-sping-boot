package com.example.chatapp.custom.exceptions;

import org.springframework.http.HttpStatus;

public class ContactOperationFailedException extends CustomException{
    public ContactOperationFailedException(String message) {
        super(message, 500, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.example.chatapp.custom.exceptions;

public class ContactOperationFailedException extends CustomException{
    public ContactOperationFailedException(String message) {
        super(message);
    }
}

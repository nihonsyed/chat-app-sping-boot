package com.example.chatapp.custom.exceptions;

public class UserAlreadyInContactException extends Exception{
    public UserAlreadyInContactException()
    {
        super("The user already is already connected!");
    }
}

package com.example.chatapp.custom.exceptions;

public class UserAlreadyInContactException extends Exception{
    public UserAlreadyInContactException()
    {
        super("User already is in this contact!");
    }
}

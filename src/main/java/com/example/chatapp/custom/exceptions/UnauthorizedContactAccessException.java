package com.example.chatapp.custom.exceptions;

public class UnauthorizedContactAccessException extends Exception{
    public UnauthorizedContactAccessException()
    {
        //todo:enum
        super("User is not authorized for this contact");
    }
}

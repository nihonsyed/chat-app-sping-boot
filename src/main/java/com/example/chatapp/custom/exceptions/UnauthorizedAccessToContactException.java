package com.example.chatapp.custom.exceptions;

public class UnauthorizedAccessToContactException extends Exception{
    public UnauthorizedAccessToContactException()
    {
        //todo:enum
        super("User is not authorized for this contact");
    }
    public UnauthorizedAccessToContactException(String message)
    {
        super(message);
    }
}

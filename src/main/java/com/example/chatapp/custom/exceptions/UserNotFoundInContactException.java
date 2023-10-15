package com.example.chatapp.custom.exceptions;

public class UserNotFoundInContactException extends Exception{
    public UserNotFoundInContactException()
    {
        //todo:enum
        super("User wasn't found in this contact!");
    }
    public UserNotFoundInContactException(String message)
    {

        super(message);
    }
}

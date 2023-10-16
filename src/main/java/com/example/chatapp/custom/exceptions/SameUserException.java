package com.example.chatapp.custom.exceptions;

public class SameUserException extends Exception{

    //todo:proper message
    public SameUserException()
    {
        super("User id can't be same for this operation!");
    }
}

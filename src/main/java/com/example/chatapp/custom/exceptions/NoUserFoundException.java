package com.example.chatapp.custom.exceptions;

public class NoUserFoundException extends Exception{
    public NoUserFoundException()
    {
        //todo:implement enum
        super("No user found!");
    }
}

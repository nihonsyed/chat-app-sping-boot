package com.example.chatapp.custom.exceptions;

public class InsufficientContactMemberException extends Exception{
    public InsufficientContactMemberException()
    {
        //todo:enum
        super("A contact must have atleast 2 members");
    }
}

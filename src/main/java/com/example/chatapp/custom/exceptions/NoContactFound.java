package com.example.chatapp.custom.exceptions;

public class NoContactFound extends Exception{

    public NoContactFound()
    {
        //todo:enum
        super("No contact found! ");
    }
    protected NoContactFound(String additionalMessage) {
        super("No contact found! "+additionalMessage);
    }
}

package com.example.chatapp.custom.exceptions;

public class ContactNotFound extends Exception{
    public ContactNotFound()
    {
        //todo:enum
        super("Contact Not Found!");
    }
}

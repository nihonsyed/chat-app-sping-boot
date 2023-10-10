package com.example.chatapp.custom.exceptions;

public class ContactFullException extends Exception{
    public ContactFullException()
    {
        super("User can't be added to this contact");
    }
}

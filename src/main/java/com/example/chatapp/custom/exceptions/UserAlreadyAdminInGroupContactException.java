package com.example.chatapp.custom.exceptions;

public class UserAlreadyAdminInGroupContactException extends Exception{
    public UserAlreadyAdminInGroupContactException()
    {
        super("User has already been an admin in this group!");
    }
}

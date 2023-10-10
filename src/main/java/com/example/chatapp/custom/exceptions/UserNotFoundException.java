package com.example.chatapp.custom.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException()
    {
        //todo:implement enum for it
        super("User couldn't be found!");
    }

}

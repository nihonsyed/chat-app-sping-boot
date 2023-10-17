package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserError;

public class NoUserFoundException extends Exception{
    public NoUserFoundException()
    {
        super(UserError.NO_ENTITY.getDescription());
    }
}

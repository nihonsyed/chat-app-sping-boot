package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserError;

public class NoUserFoundException extends CustomException {
    public NoUserFoundException()
    {
        super(UserError.NO_ENTITY.getDescription());
    }
}

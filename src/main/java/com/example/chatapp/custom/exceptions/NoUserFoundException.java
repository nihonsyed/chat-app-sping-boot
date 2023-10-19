package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserError;
import org.springframework.http.HttpStatus;

public class NoUserFoundException extends CustomException {
    public NoUserFoundException()
    {
        super(UserError.NO_ENTITY.getDescription(), 404, HttpStatus.NOT_FOUND);
    }
}

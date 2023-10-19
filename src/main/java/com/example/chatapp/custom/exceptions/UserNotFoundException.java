package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserError;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException()
    {
        super(UserError.NOT_FOUND.getDescription(), 404, HttpStatus.NOT_FOUND);
    }

}

package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserContactError;
import org.springframework.http.HttpStatus;

public class UserSignUpFailedException extends CustomNestedException {
    public UserSignUpFailedException(Exception rootException) {
        super(UserContactError.SIGN_UP_FAILED.getDescription(), rootException,500,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

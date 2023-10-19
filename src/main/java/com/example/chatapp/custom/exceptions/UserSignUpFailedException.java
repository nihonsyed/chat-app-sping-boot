package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserContactError;

public class UserSignUpFailedException extends CustomNestedException {
    public UserSignUpFailedException(Exception rootException) {
        super(UserContactError.SIGN_UP_FAILED.getDescription(), rootException);
    }
}

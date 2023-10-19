package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserContactError;
import org.springframework.http.HttpStatus;

public class UserIsNotInContactException extends CustomException {
    public UserIsNotInContactException(int statusCode, HttpStatus httpStatus) {
        super(UserContactError.NOT_A_CONTACT_MEMBER.getDescription(), statusCode, httpStatus);

    }
    public UserIsNotInContactException(String message,int statusCode,HttpStatus httpStatus) {
        super(message, statusCode, httpStatus);
    }
}

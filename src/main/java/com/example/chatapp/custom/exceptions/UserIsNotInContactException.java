package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserContactError;

public class UserIsNotInContactException extends Exception{
    public UserIsNotInContactException()
    {
        //todo:enum
        super(UserContactError.NOT_A_CONTACT_MEMBER.getDescription());

    }
    public UserIsNotInContactException(String message)
    {
        super(message);
    }
}

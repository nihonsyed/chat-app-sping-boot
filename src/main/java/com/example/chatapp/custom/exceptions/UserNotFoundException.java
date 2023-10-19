package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserContactError;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException()
    {
        super(UserContactError.NOT_A_CONTACT_MEMBER.getDescription());
    }

}

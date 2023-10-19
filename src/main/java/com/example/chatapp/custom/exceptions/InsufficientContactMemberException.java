package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;
import org.springframework.http.HttpStatus;

public class InsufficientContactMemberException extends CustomException{
    public InsufficientContactMemberException()
    {

        super(ContactErrors.INSUFFICIENT_MEMBER.getDescription(), 403, HttpStatus.FORBIDDEN);
    }

    public InsufficientContactMemberException(String message) {
        super(message, 403,HttpStatus.FORBIDDEN);
    }
}

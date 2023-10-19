package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;

public class InsufficientContactMemberException extends Exception{
    public InsufficientContactMemberException()
    {

        super(ContactErrors.INSUFFICIENT_MEMBER.getDescription());
    }

    public InsufficientContactMemberException(String message) {
        super(message);
    }
}

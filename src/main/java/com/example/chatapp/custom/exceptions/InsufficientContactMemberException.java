package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;

public class InsufficientContactMemberException extends Exception{
    public InsufficientContactMemberException()
    {
        //todo:enum
        super(ContactErrors.INSUFFICIENT_MEMBER.getDescription());
    }
}

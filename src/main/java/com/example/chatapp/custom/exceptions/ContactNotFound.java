package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;

public class ContactNotFound extends Exception{
    public ContactNotFound()
    {
        super(ContactErrors.NOT_FOUND.getDescription());
    }
}

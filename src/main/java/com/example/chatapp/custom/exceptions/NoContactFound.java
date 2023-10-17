package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;

public class NoContactFound extends Exception{

    public NoContactFound()
    {
        super(ContactErrors.NO_ENTITY.getDescription());
    }
    protected NoContactFound(String additionalMessage) {
        super("No contact found! "+additionalMessage);
    }
}

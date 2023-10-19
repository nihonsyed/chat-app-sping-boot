package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;
import org.springframework.http.HttpStatus;

public class NoContactFound extends CustomException{

    public NoContactFound()
    {
        super(ContactErrors.NO_ENTITY.getDescription(), 404, HttpStatus.NOT_FOUND);
    }
    protected NoContactFound(String additionalMessage) {
        super(ContactErrors.NO_ENTITY.getDescription().concat(additionalMessage), 404, HttpStatus.NOT_FOUND);
    }
}

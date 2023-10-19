package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;
import org.springframework.http.HttpStatus;

public class ContactNotFound extends CustomException{
    public ContactNotFound()
    {
        super(ContactErrors.NOT_FOUND.getDescription(),404, HttpStatus.NOT_FOUND);
    }
}

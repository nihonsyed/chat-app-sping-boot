package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.contact.ContactErrors;
import com.example.chatapp.enums.user.UserContactError;

public class IllegalContactOperation extends Exception {

    private String additionalMessage;

    public IllegalContactOperation() {
        super(UserContactError.ILLEGAL_OPERATION.getDescription());
    }

    public IllegalContactOperation(String additionalMessage) {
        this();
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getMessage() {
        return (additionalMessage != null) ? super.getMessage().concat(additionalMessage) : super.getMessage();
    }

}
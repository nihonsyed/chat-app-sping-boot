package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.user.UserContactError;
import org.springframework.http.HttpStatus;

public class IllegalContactOperation extends CustomException{

    private String additionalMessage;

    public IllegalContactOperation() {
        super(UserContactError.ILLEGAL_OPERATION.getDescription(),403, HttpStatus.FORBIDDEN);
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
package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.message.MessageErrors;

import java.util.Objects;

public class MessageSendingFailureException extends CustomNestedException{


    public MessageSendingFailureException( Exception rootException) {
        super(MessageErrors.SENDING_FAILURE.getDescription(), rootException);
    }
}

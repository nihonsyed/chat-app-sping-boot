package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.message.MessageErrors;
import org.springframework.http.HttpStatus;


public class MessageSendingFailureException extends CustomNestedException{


    public MessageSendingFailureException( Exception rootException) {
        super(MessageErrors.SENDING_FAILURE.getDescription(), rootException,500,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

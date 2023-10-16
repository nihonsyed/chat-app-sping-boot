package com.example.chatapp.custom.exceptions;

public class MessageSendingFailureException extends Exception{

    public MessageSendingFailureException()
    {
        super("Message failed to send!");
    }
}

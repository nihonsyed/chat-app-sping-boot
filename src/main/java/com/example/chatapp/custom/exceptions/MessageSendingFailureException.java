package com.example.chatapp.custom.exceptions;

import com.example.chatapp.enums.message.MessageErrors;

import java.util.Objects;

public class MessageSendingFailureException extends Exception{

    private RuntimeException triggererException;

    public MessageSendingFailureException()
    {
        super(MessageErrors.SENDING_FAILURE.getDescription());
    }

    public MessageSendingFailureException(RuntimeException mainException)
    {
        this();
        this.triggererException =mainException;
    }
    @Override
    public Throwable getCause()
    {
        return (Objects.isNull(triggererException))?
                super.getCause(): triggererException.getCause();
    }




}

package com.example.chatapp.custom.exceptions;

public class IllegalContactOperation extends Exception {

    private String additionalMessage;

    public IllegalContactOperation() {
        //todo:enum
        super("This operation is not allowed for this contact! ");
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
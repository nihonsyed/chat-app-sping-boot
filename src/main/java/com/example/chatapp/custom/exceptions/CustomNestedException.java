package com.example.chatapp.custom.exceptions;

public class CustomNestedException extends CustomException {

    private final Exception rootException;

    public CustomNestedException(String message, Exception rootException) {
        super(message, rootException.getCause());
        this.rootException = rootException;
    }

    public Exception getRootException() {
        return rootException;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return rootException.getStackTrace();
    }

    @Override
    public void printStackTrace() {
        rootException.printStackTrace();
    }


}

package com.example.chatapp.custom.exceptions;

public class UserHasNoContactException extends NoContactFound{
    public UserHasNoContactException() {
        //todo:enum & proper value
        super("for this user");
    }
}

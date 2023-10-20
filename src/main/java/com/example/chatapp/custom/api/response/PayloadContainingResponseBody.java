package com.example.chatapp.custom.api.response;

import lombok.Getter;

@Getter
public class PayloadContainingResponseBody extends SuccessResponseBody{

    private final Object data;
    public PayloadContainingResponseBody(String message, int status, Object data) {
        super(message, status);
        this.data = data;
    }
}

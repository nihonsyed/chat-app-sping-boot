package com.example.chatapp.custom.api.response;

import lombok.Data;

@Data
public class SuccessResponseBody {

    private final String message;
    private final int status;

}

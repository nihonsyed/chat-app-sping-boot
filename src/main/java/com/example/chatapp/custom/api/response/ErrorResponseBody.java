package com.example.chatapp.custom.api.response;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponseBody {
    //todo:keep an identifer for tracing exception in the log

    private final int statusCode;
    private final String error;
    private final String message;
    private final String path;
    private final Date timeStamp;
}

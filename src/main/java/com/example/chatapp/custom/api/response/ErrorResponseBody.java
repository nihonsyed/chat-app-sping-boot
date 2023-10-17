package com.example.chatapp.custom.api.response;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponseBody {
    //todo:keep an identifer for tracing exception in the log
    private final String errorCode;
    private final String error;
    private final Integer statusCode;
    private final String statusName;
    private final String path;
    private final String method;
    private final Date timestamp;
}

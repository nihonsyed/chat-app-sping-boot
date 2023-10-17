package com.example.chatapp.enums.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageErrors {

    SENDING_FAILURE("Message failed to send!");

    private final String description;
}

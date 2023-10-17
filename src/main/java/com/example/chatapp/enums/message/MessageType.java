package com.example.chatapp.enums.message;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {

    TEXT("text",0),
    IMAGE("image",1),
    AUDIO("audio",2),
    VIDEO("video",3);

    private final String label;
    private final int code;

}

package com.example.chatapp.models.dto.chat;

import com.example.chatapp.entities.messages.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//todo: ? can it be made abstract
public class ChatDto {

    private String chatName;
    private List<Message> messages;

    public ChatDto(String chatName) {
        this.chatName = chatName;
    }
}

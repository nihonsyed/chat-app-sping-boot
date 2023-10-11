package com.example.chatapp.dto.contact;

import com.example.chatapp.models.messages.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//todo: ? can it be made abstract
public class ChatHistoryDto {

    private String chatName;
    private List<Message> messages;

    public ChatHistoryDto(String chatName) {
        this.chatName = chatName;
    }
}

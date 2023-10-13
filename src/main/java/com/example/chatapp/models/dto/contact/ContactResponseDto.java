package com.example.chatapp.models.dto.contact;

import com.example.chatapp.entities.messages.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class ContactResponseDto {
    private String name;
    private Date generatedTime;
    private Set<Message> messages;
}

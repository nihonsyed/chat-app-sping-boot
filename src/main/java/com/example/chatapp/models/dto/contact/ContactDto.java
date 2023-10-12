package com.example.chatapp.models.dto.contact;

import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class ContactDto {

    private String name;
    private Date generatedTime;
    private Set<Message> messages;
    private Set<User> members;
}

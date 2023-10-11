package com.example.chatapp.dto.contact;

import com.example.chatapp.models.messages.Message;
import com.example.chatapp.models.users.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class Contact {

    private String name;
    private Date generatedTime;
    private Set<Message> messages;
    private Set<User> members;
}

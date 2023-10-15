package com.example.chatapp.services.message;

import com.example.chatapp.entities.messages.TextMessage;
import com.example.chatapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository repository;


    @Override
    public void sendTextMessage(TextMessage textMessage) {
        repository.save(textMessage);
    }
}

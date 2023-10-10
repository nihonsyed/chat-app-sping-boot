package com.example.chatapp.models.messages;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "text")
public class TextMessage extends Message {


    public TextMessage() {
        super();

    }


    @Override
    public Object getContent() {
       return super.getContentBody();
    }

}

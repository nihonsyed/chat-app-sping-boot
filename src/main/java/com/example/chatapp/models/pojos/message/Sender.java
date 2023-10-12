package com.example.chatapp.models.pojos.message;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Sender implements MessageProcessor {

    private Long id;
    private String name;


}

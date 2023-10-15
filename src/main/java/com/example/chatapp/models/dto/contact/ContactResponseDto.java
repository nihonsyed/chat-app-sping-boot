package com.example.chatapp.models.dto.contact;

import com.example.chatapp.models.dto.message.MessageInContactResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class ContactResponseDto {
    private Long id;
    private String name;
    private Date generatedTime;

    private Set<MessageInContactResponseDto> messages;
}

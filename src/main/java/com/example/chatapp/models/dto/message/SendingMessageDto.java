package com.example.chatapp.models.dto.message;

import com.example.chatapp.entities.contacts.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SendingMessageDto {


    @Schema(example = "delivered",description = "Status of message (delivered/sent/failed)")
    private String status;
    @Schema(example = "Hi,there!",description = "Main content of the message")
    private String contentBody;
}

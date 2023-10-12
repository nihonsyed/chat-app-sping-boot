package com.example.chatapp.models.dto.message;

import com.example.chatapp.entities.contacts.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {

    @Schema(example = "1990-01-01",description = "Delivery time of the message")
    private Date deliveryTime;
    @Schema(example = "delivered",description = "Status of message (delivered/sent/failed)")
    private String status;
    private Date sentTime;
    private Contact contact;
    @Schema(example = "Hi,there!",description = "Main content of the message")
    private String contentBody;
}

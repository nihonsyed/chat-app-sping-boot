package com.example.chatapp.models.dto.message;

import com.example.chatapp.models.dto.user.SenderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageResponseDto {


    private Date deliveryTime;
    private String status;
    private Date sentTime;
    private String contentBody;
    private SenderDto messageSender;
}

package com.example.chatapp.models.dto.message;

import com.example.chatapp.models.dto.user.ContactMemberDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageInContactResponseDto {

    private Long id;
    private String status;
    private Date sentTime;
    protected String contentBody;
    private ContactMemberDto sender;

}

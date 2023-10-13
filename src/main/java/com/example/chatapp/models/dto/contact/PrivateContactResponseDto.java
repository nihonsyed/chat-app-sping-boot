package com.example.chatapp.models.dto.contact;

import com.example.chatapp.models.dto.user.PrivateContactMemberDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class PrivateContactResponseDto extends ContactResponseDto {

    @Getter
    @Setter
    Set<PrivateContactMemberDto> members;
}

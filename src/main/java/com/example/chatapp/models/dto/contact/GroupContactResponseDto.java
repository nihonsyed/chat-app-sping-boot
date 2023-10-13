package com.example.chatapp.models.dto.contact;

import com.example.chatapp.models.dto.user.GroupContactMemberDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GroupContactResponseDto extends ContactResponseDto {

    Set<GroupContactMemberDto> members;
}

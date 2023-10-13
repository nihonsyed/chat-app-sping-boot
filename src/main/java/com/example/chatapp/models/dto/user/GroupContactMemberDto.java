package com.example.chatapp.models.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupContactMemberDto extends PrivateContactMemberDto {

    private boolean isAdmin;

    public GroupContactMemberDto() {

    }

}

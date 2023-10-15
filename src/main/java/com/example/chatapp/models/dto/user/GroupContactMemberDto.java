package com.example.chatapp.models.dto.user;

import lombok.Getter;

@Getter
public class GroupContactMemberDto extends ContactMemberDto {

    private boolean isAdmin;

    //todo:setting true if admin
    public void setIsAdmin()
    {

    }

}

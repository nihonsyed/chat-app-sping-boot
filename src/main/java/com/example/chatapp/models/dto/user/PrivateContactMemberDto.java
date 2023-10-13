package com.example.chatapp.models.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivateContactMemberDto {

    private Long id;
    private String firstName;
    private String lastName;
    private boolean isActive;
}

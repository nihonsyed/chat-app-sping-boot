package com.example.chatapp.models.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ContactMemberDto {

    private Long id;
    private String firstName;
    private String lastName;
    private boolean isActive;
}

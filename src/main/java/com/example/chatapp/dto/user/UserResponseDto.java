package com.example.chatapp.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserResponseDto {
    private Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String nationality;
    private String email;
}

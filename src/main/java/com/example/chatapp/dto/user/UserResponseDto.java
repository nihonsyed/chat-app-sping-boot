package com.example.chatapp.dto.user;

import com.example.chatapp.models.contacts.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
    private Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String nationality;
    private String email;
    private Set<Contact> contacts;

}

package com.example.chatapp.enums.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserContactError {

    UNAUTHORIZED_ACCESS("Unauthorized access to the contact"),
    ILLEGAL_OPERATION("Illegal operation on this contact"),
    ALREADY_ADMIN("User is already an admin of this group"),
    ALREADY_MEMBER("User is already a member of this contact"),
    NOT_FOUND("User not found in this contact");

    private final String description;

}

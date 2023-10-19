package com.example.chatapp.enums.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserContactError {


    ILLEGAL_OPERATION("Illegal operation on this contact"),
    ALREADY_ADMIN("User is already an admin of this group"),
    ALREADY_CONTACT_MEMBER("User is already a member of this contact"),
    SIGN_UP_FAILED("Failed to sign up user!"),
    NOT_A_CONTACT_MEMBER("User is not a contact member"),
    SELF_ADDITION("User can't add him/herself in a contact!"),
    SELF_CONTACT("User can't make a contact with him/herself!"),
    SELF_ADMIN_ASSIGNMENT("User can't make a him/herself admin in a group!")
    ;

    private final String description;

}

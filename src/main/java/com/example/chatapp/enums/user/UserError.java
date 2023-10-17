package com.example.chatapp.enums.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserError {
    NO_ENTITY("No user found!"),
    NOT_FOUND("User not found");

    private final String description;
}

package com.example.chatapp.enums.contact;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContactErrors {
    NOT_FOUND("Contact Not Found"),
    NO_ENTITY("No Contact Entity Provided"),
    INSUFFICIENT_MEMBER("Insufficient Members (A Contact Requires At Least Two Members)");


    private String description;

}

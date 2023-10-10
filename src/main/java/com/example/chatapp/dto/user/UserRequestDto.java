package com.example.chatapp.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRequestDto {
    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @Schema(description = "User's date of birth", example = "1990-01-01")
    private Date dateOfBirth;

    @Schema(description = "User's nationality", example = "US")
    private String nationality;

    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User's password", example = "P@ssw0rd")
    private String password;
}

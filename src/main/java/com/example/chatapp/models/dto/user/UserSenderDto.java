package com.example.chatapp.models.dto.user;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSenderDto  {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean isActive;
}

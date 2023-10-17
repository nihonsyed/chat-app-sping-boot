package com.example.chatapp.entities.contacts;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "0")
@NoArgsConstructor
public class PrivateContact extends Contact {

}

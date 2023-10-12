package com.example.chatapp.entities.contacts;

import com.example.chatapp.custom.exceptions.ContactFullException;
import com.example.chatapp.custom.exceptions.UserAlreadyInContactException;
import com.example.chatapp.entities.users.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//todo:use numeric value as discriminator
@DiscriminatorValue(value = "1")
public class GroupContact extends Contact{

    @Override
    public void addMember(User newMember) throws UserAlreadyInContactException, ContactFullException {
        super.addMember(newMember);
    }
}

package com.example.chatapp.models.contacts;

import com.example.chatapp.custom.exceptions.ContactFullException;
import com.example.chatapp.custom.exceptions.UserAlreadyInContactException;
import com.example.chatapp.models.users.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//todo:use numeric value as discriminator
@DiscriminatorValue("group")
public class GroupContact extends Contact{

    @Override
    public void addMember(User newMember) throws UserAlreadyInContactException, ContactFullException {
        super.addMember(newMember);
    }
}

package com.example.chatapp.models.contacts;

import com.example.chatapp.custom.exceptions.ContactFullException;
import com.example.chatapp.custom.exceptions.UserAlreadyInContactException;
import com.example.chatapp.models.users.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.HashSet;

@Entity
//todo:use numeric value as discriminator
@DiscriminatorValue("private")
public class PrivateContact extends Contact {


    public PrivateContact() {
        members = new HashSet<>();
    }

    public PrivateContact(User member1, User member2) throws UserAlreadyInContactException, ContactFullException {
        this();
        this.addMember(member1);
        this.addMember(member2);
    }

    @Override
    public void addMember(User newMember) throws ContactFullException, UserAlreadyInContactException {
        //todo:  enum to store 1
        if (members.size() > 1) throw new ContactFullException();
        super.addMember(newMember);
    }
}

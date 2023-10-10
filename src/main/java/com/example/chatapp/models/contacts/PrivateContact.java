package com.example.chatapp.models.contacts;

import com.example.chatapp.models.users.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.HashSet;

@Entity
@DiscriminatorValue("private")
public class PrivateContact extends Contact {


    public PrivateContact() {
        members = new HashSet<>();
    }

    @Override
    public void addMember(User newMember) {
        members.add(newMember);
        newMember.getContacts().add(this);
    }
}

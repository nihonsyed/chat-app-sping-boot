package com.example.chatapp.entities.contacts;

import com.example.chatapp.custom.exceptions.ContactFullException;
import com.example.chatapp.custom.exceptions.UserAlreadyInContactException;
import com.example.chatapp.entities.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//todo:use numeric value as discriminator
@DiscriminatorValue(value = "1")
public class GroupContact extends Contact {

    @Override
    public void addMember(User newMember) throws UserAlreadyInContactException, ContactFullException {
        super.addMember(newMember);
    }

    @ManyToMany
    @JoinTable(name = "group_admins", joinColumns = @JoinColumn(name = "group_contact_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    Set<User> admins = new HashSet<>();
}

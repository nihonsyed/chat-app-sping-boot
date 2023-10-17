package com.example.chatapp.entities.contacts;

import com.example.chatapp.entities.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "1")
@Getter
@Setter
public class GroupContact extends Contact {



    @ManyToMany
    @JoinTable(name = "group_admins", joinColumns = @JoinColumn(name = "group_contact_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    Set<User> admins = new HashSet<>();
}

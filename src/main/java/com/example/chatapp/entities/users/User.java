package com.example.chatapp.entities.users;

import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.GroupContact;
import com.example.chatapp.entities.messages.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private Date dateOfBirth;
    private String nationality;
    private String email;
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JoinTable(name = "user_contact", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
    @JsonManagedReference
    private Set<Contact> contacts;

    @ManyToMany(mappedBy = "admins")
    @JsonIgnore
    private Set<GroupContact> adminOfGroups = new HashSet<>();


    @OneToMany(mappedBy = "sender",
            cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}

    )
    private Set<Message> sentMessages=new HashSet<>();




}

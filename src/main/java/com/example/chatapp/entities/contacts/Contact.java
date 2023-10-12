package com.example.chatapp.entities.contacts;

import com.example.chatapp.custom.exceptions.ContactFullException;
import com.example.chatapp.custom.exceptions.UserAlreadyInContactException;
import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.INTEGER)
@Getter
@Setter
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "genaration_time")
    protected Date generatedTime;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)
    @JsonManagedReference
    protected Set<Message> messages;



    @ManyToMany(mappedBy = "contacts")
    @JsonBackReference
    protected Set<User> members;

    public Contact()
    {
        this.generatedTime=new Date();
    }

    public void addMessage(Message message)
    {
        messages.add(message);
    }

    public void addMember(User newMember) throws ContactFullException, UserAlreadyInContactException
    {
        if(members.contains(newMember))
            throw new UserAlreadyInContactException();
        members.add(newMember);
        //todo:?? for unicity
        newMember.getContacts().add(this);
    }

    //todo:work with admin

}

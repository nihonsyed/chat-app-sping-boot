package com.example.chatapp.models.contacts;

import com.example.chatapp.custom.exceptions.ContactFullException;
import com.example.chatapp.custom.exceptions.UserAlreadyInContactException;
import com.example.chatapp.models.messages.Message;
import com.example.chatapp.models.messages.TextMessage;
import com.example.chatapp.models.users.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "genaration_time")
    protected Date generatedTime;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)
    protected Set<Message> messages;



    @ManyToMany(mappedBy = "contacts")
    protected Set<User> members;


    public void addMessage(Message message)
    {

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

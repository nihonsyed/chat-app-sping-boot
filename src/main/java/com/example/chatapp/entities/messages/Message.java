package com.example.chatapp.entities.messages;

import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.pojos.message.MessageProcessor;
import com.example.chatapp.models.pojos.message.Sender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
public abstract class Message {

    @Column(name = "content")
    @Setter
    protected String contentBody;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "delivery_time")
    private Date deliveryTime;
    @Setter
    @Column(name = "status")
    private String status;
    @Setter
    @Column(name = "sent_time")
    private Date sentTime;
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    @Setter
    @JsonBackReference
    private Contact contact;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @Setter
    @JsonBackReference
    private User sender;


    public Message() {
        this.deliveryTime = new Date();
    }

    public abstract <T> T getContent();


}

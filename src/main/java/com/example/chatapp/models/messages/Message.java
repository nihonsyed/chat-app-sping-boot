package com.example.chatapp.models.messages;

import com.example.chatapp.models.contacts.Contact;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
public abstract class Message<T> {

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

    @Column(name = "content")
    @Setter
    protected String contentBody;

    //todo:add sender and contact


    public Message() {
        this.deliveryTime = new Date();
    }

    public abstract T getContent();


}

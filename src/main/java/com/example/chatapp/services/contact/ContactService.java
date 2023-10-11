package com.example.chatapp.services.contact;



import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.models.contacts.Contact;
import com.example.chatapp.models.messages.Message;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public interface ContactService {

    List<Contact> findAll() throws NoContactFound;

    Contact findById(Long id) throws ContactNotFound;

    void addMessageById(Message message, Long contactId) throws ContactNotFound;

    void setPrivateContactsDefaultNamesForResponse(Long userId, @NotNull Set<Contact> contacts) throws InsufficientContactMemberException;


    //todo:rest of the methods



}

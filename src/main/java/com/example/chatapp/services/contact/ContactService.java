package com.example.chatapp.services.contact;



import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.models.contacts.Contact;
import com.example.chatapp.models.messages.Message;

import java.util.List;

public interface ContactService {

    List<Contact> findAll() throws NoContactFound;

    Contact findById(Long id) throws ContactNotFound;

    void addMessageById(Message message, Long contactId) throws ContactNotFound;


    //todo:rest of the methods



}

package com.example.chatapp.services.contact;



import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.dto.contact.PrivateContactResponseDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public interface ContactService {

    List<Contact> findAll() throws NoContactFound;

    Contact findById(Long id) throws ContactNotFound;

    void deleteById(Long id);

    void addMessageById(Message message, Long contactId) throws ContactNotFound;

    void setPrivateContactsDefaultNamesForResponse(Long userId, @NotNull Set<Contact> contacts) throws InsufficientContactMemberException;

    List<Contact> findByType(String type) throws NoContactFound;

    Contact findPrivateContactByUsers(User user, User searchedUser) throws NoContactFound, ContactNotFound;

    PrivateContactResponseDto getPrivateContactResponseById(Long userId,Long contactId) throws ContactNotFound;


    //todo:rest of the methods



}

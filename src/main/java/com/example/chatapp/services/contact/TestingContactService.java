package com.example.chatapp.services.contact;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.GroupContact;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.repositories.ContactRepository;

import java.util.Set;

public interface TestingContactService {

   <T> void  makeContact(User requestingUser, T addableUser) throws InsufficientContactMemberException, UserNotFoundException, UserAlreadyInContactException;
    void leaveContact(User user, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound;

    Contact findById(Long id) throws ContactNotFound;

    void addMessage(User user, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException, MessageSendingFailureException;
     default  boolean isMember(Set<User> members, User user)
    {
        return members.contains(user);
    }

    default boolean isContactMember(User user, Contact contact) throws UnauthorizedAccessToContactException {

        if (!contact.getMembers().contains(user)) throw new UnauthorizedAccessToContactException();
        return true;

    }

    default void removeMember() throws IllegalContactOperation {
        throw new IllegalContactOperation();
    }

    default void addMember(User user,  User addableUser, Long groupContactId) throws ContactFullException,UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, UserAlreadyInContactException, IllegalContactOperation, SameUserException {
        throw new ContactFullException();
    }

    default void leaveContact(User user, Long contactId, ContactRepository repository) throws ContactNotFound, UnauthorizedAccessToContactException {
        Contact contact = repository.findById(contactId).orElseThrow(ContactNotFound::new);
        Set<User> members=contact.getMembers();
        if(!isMember(members,user))
            throw new UnauthorizedAccessToContactException();
        members.remove(user);
        user.getContacts().remove(contact);

        if(contact instanceof GroupContact)
        {
           ((GroupContact) contact).getAdmins().remove(user);
        }

        if(contact.getMembers().isEmpty())
             repository.delete(contact);
        // repository.save(contact);
    }


}

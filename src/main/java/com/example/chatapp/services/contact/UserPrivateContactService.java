package com.example.chatapp.services.contact;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.PrivateContact;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.enums.user.UserContactError;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.repositories.ContactRepository;
import com.example.chatapp.services.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("private")
public class UserPrivateContactService implements UserContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private MessageService messageService;


    @Override
    public <T> void makeContact(User requestingUser, T addableUser) throws InsufficientContactMemberException, UserNotFoundException {
        if(addableUser instanceof User)
        {
            User addingUser=(User) addableUser;

            if(hasContact(requestingUser,addingUser))
             throw  new ContactOperationFailedException(UserContactError.ALREADY_CONTACT_MEMBER.getDescription());

            else
            {
                PrivateContact contact=new PrivateContact();
                contact.getMembers().add(addingUser);
                contact.getMembers().add(requestingUser);
                addingUser.getContacts().add(contact);
                requestingUser.getContacts().add(contact);
                repository.save(contact);

            }
        }
    }




    @Override
    public void leaveContact(User user, Long contactId) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, NoContactFound {

        leaveContact(user,contactId,repository);
    }

    @Override
    public Contact findById(Long id) throws ContactNotFound {
        return repository.findById(id).orElseThrow(ContactNotFound::new);
    }

    @Override
    public void addMessage(User user, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, IllegalAccessException, MessageSendingFailureException {
        Contact contact=findById(contactId);
        if(!contact.getMembers().contains(user))
            throw  new UserIsNotInContactException();
        messageService.send(contact,user,addableMessageDto,messageTypeCode);
    }

    private boolean hasContact(User requestingUser, User addingUser) {
        return requestingUser.getContacts().stream()
                .anyMatch(contact -> contact instanceof PrivateContact &&
                        contact.getMembers().contains(requestingUser) &&
                        contact.getMembers().contains(addingUser));
    }
}

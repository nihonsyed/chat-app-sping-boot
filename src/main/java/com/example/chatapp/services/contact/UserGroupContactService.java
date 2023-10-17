package com.example.chatapp.services.contact;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.GroupContact;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.enums.contact.ContactTypes;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.repositories.ContactRepository;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;



@Service("group")
public class UserGroupContactService implements UserContactService {


    @Autowired
    private ContactRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public <T> void makeContact(User requestingUser, T addableUsers) throws InsufficientContactMemberException, UserNotFoundException {
        //if it exist in the list

        if(addableUsers instanceof Set)
        {

           Set<User> addableUsersSet=(Set<User>) addableUsers;
           addableUsersSet.remove(requestingUser);
           GroupContact newContact=new GroupContact();
            newContact.getMembers().addAll(addableUsersSet);
            for(User addableUser:addableUsersSet)
                addableUser.getContacts().add(newContact);
            requestingUser.getContacts().add(newContact);
            newContact.getMembers().add(requestingUser);
            newContact.getAdmins().add(requestingUser);
            repository.save(newContact);

       }

    }

    @Override
    public void leaveContact( User user, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound {

      leaveContact(user,contactId,repository);
    }

    @Override
    public Contact findById(Long id) throws ContactNotFound {
        return repository.findById(id).orElseThrow(ContactNotFound::new);
    }

    @Override
    public void addMessage(User user, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException, MessageSendingFailureException {
        Contact contact=findById(contactId);

        if(contact.getMembers().size()< ContactTypes.GROUP.getMinMembers())
            throw new MessageSendingFailureException();

        messageService.send(contact,user,addableMessageDto,messageTypeCode);
    }
   public void makeAdmin(User user, User newAdmin, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, IllegalContactOperation, UserAlreadyAdminInGroupContactException, SameUserException
    {
        Contact contact =findById(groupContactId);
        if (!(contact instanceof GroupContact)) throw new IllegalContactOperation();

        if (isAdmin(user, (GroupContact) contact) && isContactMember(user, contact) && isContactMember(newAdmin,contact)) {
            Set<User> admins=((GroupContact) contact).getAdmins();
            if (admins.contains(newAdmin))
                throw new UserAlreadyAdminInGroupContactException();
            else
              { admins.add(newAdmin);
                repository.save(contact);}

        }
    }

    public void addMember( User user,  User addableUser, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, UserAlreadyInContactException, IllegalContactOperation, SameUserException {

        Contact contact = findById(groupContactId);
        if (!(contact instanceof GroupContact)) throw new IllegalContactOperation();
        if (isAdmin(user, (GroupContact) contact) && isContactMember(user, contact)) {
            Set<User> members=contact.getMembers();
            if(members.contains(addableUser))
                throw new UserAlreadyInContactException();
            members.add(addableUser);
            addableUser.getContacts().add(contact);
            repository.save(contact);
        }

    }

    public void removeMember(User user, User removeableUser, Long groupContactId) throws UserNotFoundException, ContactNotFound, ContactFullException, IllegalContactOperation, UnauthorizedAccessToContactException, UserNotFoundInContactException {

        Contact contact = findById(groupContactId);
        if (!(contact instanceof GroupContact)) throw new IllegalContactOperation();
        if (isAdmin(user, (GroupContact) contact) && isContactMember(user, contact)) {
            //todo:throw exception if it isn't found
            ((GroupContact) contact).getAdmins().remove(removeableUser);
            Set<User> members= contact.getMembers();
            if(!members.contains(removeableUser))
                throw new UserNotFoundInContactException();

            members.remove(removeableUser);
            removeableUser.getContacts().remove(contact);
            repository.save(contact);
        }
    }

    private boolean isAdmin(User user, GroupContact contact) throws IllegalContactOperation {
        if (!contact.getAdmins().contains(user)) throw new IllegalContactOperation();
        return true;
    }

}

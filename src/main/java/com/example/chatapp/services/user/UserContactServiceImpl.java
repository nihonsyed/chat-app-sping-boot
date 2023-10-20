package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.enums.user.UserContactError;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.contact.UserGroupContactService;
import com.example.chatapp.services.contact.UserPrivateContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserContactServiceImpl implements UserContactSerivce {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserPrivateContactService privateContactService;

    @Autowired
    private UserGroupContactService groupContactService;

    @Override
    public void makePrivateContact(Long requestingUsersId, Long addableUserId) throws UserNotFoundException,  InsufficientContactMemberException, IllegalContactOperation {
        if (requestingUsersId.equals(addableUserId)) throw new IllegalContactOperation(UserContactError.SELF_CONTACT.getDescription());
        User user = getById(repository,requestingUsersId);
        User addableUser = getById(repository,addableUserId);
        privateContactService.makeContact(user,addableUser);
    }

    @Override
    public void addMember(Long userId, Long addableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UserIsNotInContactException, IllegalContactOperation{
        if(userId.equals(addableUserId)) throw new IllegalContactOperation(UserContactError.SELF_ADDITION.getDescription());
        User user = getById(repository,userId);
        User addableUser = getById(repository,addableUserId);
        groupContactService.addMember(user,addableUser,groupContactId);

    }

    @Override
    public void makeAdmin(Long userId, Long newAdminId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UserIsNotInContactException, IllegalContactOperation {
        if(userId.equals(newAdminId)) throw new IllegalContactOperation(UserContactError.SELF_ADMIN_ASSIGNMENT.getDescription());
        User user = getById(repository,userId);
        User newAdmin = getById(repository,newAdminId);
        groupContactService.makeAdmin(user,newAdmin,groupContactId);


    }

    @Override
    public void removeMember(Long userId, Long removeableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound,  IllegalContactOperation, UserIsNotInContactException {
        User user = getById(repository,userId);
        User removeableUser = getById(repository,removeableUserId);
        groupContactService.removeMember(user,removeableUser,groupContactId);
    }

    @Override
    public void makeGroupContact(Long userId, Set<Long> addableUserIds) throws UserNotFoundException, InsufficientContactMemberException {
        if (addableUserIds == null || addableUserIds.isEmpty()) throw new InsufficientContactMemberException();

        User user = getById(repository,userId);
        Set<User> addableUsers = new HashSet<>();
        for (Long id : addableUserIds) {
            if (!id.equals(userId)) {
                User byId = getById(repository,id);
                addableUsers.add(byId);
            }
        }

        groupContactService.makeContact(user, addableUsers);
    }

    @Override
    public void sendMessage(Long userId, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, IllegalAccessException, MessageSendingFailureException {

        User user = getById(repository,userId);
        privateContactService.addMessage(user,contactId,addableMessageDto,messageTypeCode);


    }

    @Override
    public void leaveContact(Long userId, Long contactId) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, NoContactFound {

        User user = getById(repository,userId);
        groupContactService.leaveContact(user,contactId);
    }


}

package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.models.dto.message.SendingMessageDto;

import java.util.Set;

public interface UserContactSerivce extends BaseUserService{
    void makePrivateContact(Long userId, Long addableUserId) throws UserNotFoundException,InsufficientContactMemberException, IllegalContactOperation;

    void addMember(Long userId, Long addableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UserIsNotInContactException, IllegalContactOperation,  ContactFullException;

    void makeAdmin(Long userId, Long newAdminId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UserIsNotInContactException, IllegalContactOperation;

    void removeMember(Long userId, Long removeableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, ContactFullException, IllegalContactOperation, UserIsNotInContactException;

    void makeGroupContact(Long userId, Set<Long> addableUserIds) throws UserNotFoundException,  ContactFullException, InsufficientContactMemberException;

    void sendMessage(Long userId, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, IllegalAccessException, MessageSendingFailureException;

    void leaveContact(Long userId, Long contactId) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, NoContactFound;
}

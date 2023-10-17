package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.models.dto.message.SendingMessageDto;

import java.util.Set;

public interface UserContactSerivce extends BaseUserService{
    void makePrivateContact(Long userId, Long addableUserId) throws UserNotFoundException, UserAlreadyInContactException, SameUserException, InsufficientContactMemberException;

    void addMember(Long userId, Long addableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, UserAlreadyInContactException, IllegalContactOperation, SameUserException, ContactFullException;

    void makeAdmin(Long userId, Long newAdminId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, IllegalContactOperation, UserAlreadyAdminInGroupContactException, SameUserException;

    void removeMember(Long userId, Long removeableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, ContactFullException, IllegalContactOperation, UnauthorizedAccessToContactException, UserNotFoundInContactException;

    void makeGroupContact(Long userId, Set<Long> addableUserIds) throws UserNotFoundException, UserAlreadyInContactException, ContactFullException, InsufficientContactMemberException;

    void sendMessage(Long userId, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException;

    void leaveContact(Long userId, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound;
}

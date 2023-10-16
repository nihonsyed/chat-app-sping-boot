package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    void save(UserRequestDto user);

    List<UserResponseDto> findAll() throws NoUserFoundException;

    UserResponseDto findById(Long id) throws UserNotFoundException, InsufficientContactMemberException;

    void deleteById(Long id) throws UserNotFoundException;

    void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException;

    void makePrivateContact(Long userId, Long addableUserId) throws UserNotFoundException, UserAlreadyInContactException, SameUserException, InsufficientContactMemberException;

    void addUserToExistingGroupContact(Long userId, Long addableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, UserAlreadyInContactException, IllegalContactOperation, SameUserException, ContactFullException;

    void makeAdmin(Long userId, Long newAdminId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, IllegalContactOperation, UserAlreadyAdminInGroupContactException, SameUserException;

    void removeFromGroupContact(Long userId, Long removeableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, ContactFullException, IllegalContactOperation, UnauthorizedAccessToContactException, UserNotFoundInContactException;

    void makeGroupContact(Long userId, Set<Long> addableUserIds) throws UserNotFoundException, UserAlreadyInContactException, ContactFullException, InsufficientContactMemberException;

    void addMessageToContactByIdWithMessageType(Long userId, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException;

    void leaveGroupContact(Long userId, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound;

    UserProfileDto getUserProfile(Long id) throws UserNotFoundException;


    //todo:implement other functionalities

}

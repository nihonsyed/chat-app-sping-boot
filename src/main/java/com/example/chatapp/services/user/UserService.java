package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.models.dto.contact.ContactDto;
import com.example.chatapp.models.dto.message.MessageDto;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    void save(UserRequestDto user);

    List<UserResponseDto> findAll() throws NoUserFoundException;

    UserResponseDto findById(Long id) throws UserNotFoundException, InsufficientContactMemberException;

    void deleteById(Long id) throws UserNotFoundException;

    void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException;

    void addNewPrivateContactToUser(Long userId, Long addableUserId) throws UserNotFoundException, IllegalAccessException, UserAlreadyInContactException, ContactFullException;

    void addMessageToContactByIdWithMessageType(Long userId, Long contactId, MessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException;

    void deleteContactById(Long userId, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound;

    UserProfileDto getUserProfile(Long id) throws UserNotFoundException;

    ContactDto findPrivateContactByUserIds(Long userId, Long searchedUsersId) throws UserNotFoundException, NoContactFound, ContactNotFound;


    //todo:implement other functionalities

}

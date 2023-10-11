package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.dto.message.MessageDto;
import com.example.chatapp.dto.user.UserRequestDto;
import com.example.chatapp.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    void save(UserRequestDto user);

    List<UserResponseDto> findAll() throws NoUserFoundException;

    UserResponseDto findById(Long id) throws UserNotFoundException;

    void deleteById(Long id) throws UserNotFoundException;

    void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException;

    void addNewPrivateContactToUser(Long userId, Long addableUserId) throws UserNotFoundException, IllegalAccessException, UserAlreadyInContactException, ContactFullException;

    void addMessageToContactByIdWithMessageType(Long userId, Long contactId, MessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedContactAccessException, ContactNotFound, IllegalAccessException;



    //todo:implement other functionalities

}

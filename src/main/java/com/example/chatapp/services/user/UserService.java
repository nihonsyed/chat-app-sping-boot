package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.dto.contact.ContactDto;
import com.example.chatapp.dto.user.UserRequestDto;
import com.example.chatapp.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    void save(UserRequestDto user);

    List<UserResponseDto> findAll() throws NoUserFoundException;

    UserResponseDto findById(Long id) throws UserNotFoundException;

    void deleteById(Long id) throws UserNotFoundException;

    void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException;


    void addNewPrivateContactToUser(Long userId, ContactDto contactDto) throws UserNotFoundException, IllegalAccessException;

    //todo:implement other functionalities

}

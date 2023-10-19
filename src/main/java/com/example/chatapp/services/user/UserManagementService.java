package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.custom.exceptions.UserSignUpFailedException;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;

import java.util.List;

public interface UserManagementService extends BaseUserService{
    void save(UserRequestDto user) throws UserSignUpFailedException;

    List<UserResponseDto> findAll() throws NoUserFoundException;

    UserResponseDto findById(Long id) throws UserNotFoundException, InsufficientContactMemberException;

    void deleteById(Long id) throws UserNotFoundException;

    void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException;



    UserProfileDto getUserProfile(Long id) throws UserNotFoundException;

}

package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.custom.exceptions.UserSignUpFailedException;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.contact.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomModelMapper modelMapper;

    @Autowired
    private ContactService contactService;

    @Override
    public void save(UserRequestDto user) throws UserSignUpFailedException {
        try {
            User newUser = modelMapper.map(user, User.class);
            repository.save(newUser);
        } catch (Exception exception) {
            throw new UserSignUpFailedException(exception);
        }
    }

    @Override
    public List<UserResponseDto> findAll() throws NoUserFoundException {
        List<User> users = repository.findAll();
        if (users.isEmpty()) throw new NoUserFoundException();
        return modelMapper.mapList(users, UserResponseDto.class);

    }

    @Override
    public UserResponseDto findById(Long id) throws UserNotFoundException, InsufficientContactMemberException {

        User foundUser = getById(repository, id);
        UserResponseDto userResponseDto = modelMapper.map(foundUser, UserResponseDto.class);
        contactService.setPrivateContactsDefaultNamesForResponse(id, userResponseDto.getContacts());
        return userResponseDto;
    }

    @Override
    public void deleteById(Long id) throws UserNotFoundException {
        getById(repository, id);
        repository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException {

        User source = modelMapper.map(updateDto, User.class);
        User userToBeUpdated = modelMapper.map(getById(repository, id), User.class);
        modelMapper.mapToUpdate(source, userToBeUpdated);
        repository.save(userToBeUpdated);
    }


    @Override
    public UserProfileDto getUserProfile(Long id) throws UserNotFoundException {
        User foundUser = getById(repository, id);
        return modelMapper.map(foundUser, UserProfileDto.class);

    }


}
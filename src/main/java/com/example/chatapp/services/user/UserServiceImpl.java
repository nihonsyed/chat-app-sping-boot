package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.dto.contact.ContactDto;
import com.example.chatapp.dto.user.UserRequestDto;
import com.example.chatapp.dto.user.UserResponseDto;
import com.example.chatapp.models.contacts.Contact;
import com.example.chatapp.models.contacts.PrivateContact;
import com.example.chatapp.models.users.User;
import com.example.chatapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomModelMapper modelMapper;

    @Override
    public void save(UserRequestDto user) {
        User newUser = modelMapper.map(user, User.class);
        repository.save(newUser);
    }

    @Override
    public List<UserResponseDto> findAll() throws NoUserFoundException {
        List<User> users = repository.findAll();
        if (users.isEmpty()) throw new NoUserFoundException();
        return modelMapper.mapList(users, UserResponseDto.class);

    }

    @Override
    public UserResponseDto findById(Long id) throws UserNotFoundException {

        User foundUser = repository.getById(id);
        return modelMapper.map(foundUser, UserResponseDto.class);
    }

    @Override
    public void deleteById(Long id) throws UserNotFoundException {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException {

        User source=modelMapper.map(updateDto,User.class);
        User userToBeUpdated=modelMapper.map(findById(id), User.class);
        modelMapper.mapToUpdate(source,userToBeUpdated);
        repository.save(userToBeUpdated);
    }

    @Override
    public void addNewPrivateContactToUser(Long userId, ContactDto contactDto) throws UserNotFoundException, IllegalAccessException {
        User user=getById(userId);
        Set<Contact> existingContacts=user.getContacts();
        PrivateContact newContact=new PrivateContact();
//         newContact=modelMapper.map(contactDto, PrivateContact.class);
         modelMapper.mapNonNullFields(contactDto,newContact);
        existingContacts.add(newContact);
        repository.save(user);
    }

    private User getById(Long id) throws UserNotFoundException {
      return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}

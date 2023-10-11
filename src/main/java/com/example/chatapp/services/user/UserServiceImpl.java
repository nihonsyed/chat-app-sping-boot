package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.dto.message.MessageDto;
import com.example.chatapp.dto.user.UserRequestDto;
import com.example.chatapp.dto.user.UserResponseDto;
import com.example.chatapp.models.contacts.Contact;
import com.example.chatapp.models.contacts.PrivateContact;
import com.example.chatapp.models.messages.Message;
import com.example.chatapp.models.messages.TextMessage;
import com.example.chatapp.models.users.User;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.contact.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomModelMapper modelMapper;

    @Autowired
    private ContactService contactService;

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
    public UserResponseDto findById(Long id) throws UserNotFoundException, InsufficientContactMemberException {

        User foundUser = getById(id);
        UserResponseDto userResponseDto= modelMapper.map(foundUser, UserResponseDto.class);
        contactService.setPrivateContactsDefaultNamesForResponse(id,userResponseDto.getContacts());
        return userResponseDto;

        //todo:set contact name to returnable UserResonseDto


    }

    @Override
    public void deleteById(Long id) throws UserNotFoundException {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException {

        User source = modelMapper.map(updateDto, User.class);
        User userToBeUpdated = modelMapper.map(getById(id), User.class);
        modelMapper.mapToUpdate(source, userToBeUpdated);
        repository.save(userToBeUpdated);
    }

    @Override
    public void addNewPrivateContactToUser(Long userId, Long addableUserId) throws UserNotFoundException, UserAlreadyInContactException, ContactFullException {
        User user = getById(userId);
        User addableUser=getById(addableUserId);
        PrivateContact contact=new PrivateContact();
        user.getContacts().add(contact);
        addableUser.getContacts().add(contact);
        repository.save(user);
        repository.save(addableUser);
    }

    @Override
    public void addMessageToContactByIdWithMessageType(Long userId, Long contactId, MessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedContactAccessException, ContactNotFound, IllegalAccessException {
        //todo:implement enum
        if(messageTypeCode==0)
        {
            TextMessage textMessage=new TextMessage();
            modelMapper.mapUsingParentClassProperties(addableMessageDto,textMessage);
            addMessageToContactById(userId,contactId,textMessage);
        }

    }



    private void addMessageToContactById(Long userId, Long contactId, Message addableMessage) throws UserNotFoundException, UnauthorizedContactAccessException, ContactNotFound {

       if(hasContact(userId,contactId))
       {
           contactService.addMessageById(addableMessage,contactId);
       }

    }

    private User getById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    private boolean hasContact(Long userId,Long contactId) throws UserNotFoundException, ContactNotFound, UnauthorizedContactAccessException {
        User user=getById(userId);
        Contact contact=contactService.findById(contactId);
        if(!user.getContacts().contains(contact))
            throw new UnauthorizedContactAccessException();
        return true;
    }
}

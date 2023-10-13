package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.models.dto.contact.ContactDto;
import com.example.chatapp.models.dto.contact.PrivateContactResponseDto;
import com.example.chatapp.models.dto.message.MessageDto;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.PrivateContact;
import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.messages.TextMessage;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.pojos.message.Sender;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.contact.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        if (users.isEmpty()) {
            throw new NoUserFoundException();
        }
        return modelMapper.mapList(users, UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(Long id) throws UserNotFoundException, InsufficientContactMemberException {
        User foundUser = getById(id);
        UserResponseDto userResponseDto = modelMapper.map(foundUser, UserResponseDto.class);
        contactService.setPrivateContactsDefaultNamesForResponse(id, userResponseDto.getContacts());
        return userResponseDto;
    }

    @Override
    public void deleteById(Long id) throws UserNotFoundException {
        User user = getById(id);
        repository.delete(user);
    }

    @Override
    public void updateById(Long id, UserRequestDto updateDto) throws IllegalAccessException, UserNotFoundException {
        User source = modelMapper.map(updateDto, User.class);
        User userToBeUpdated = getById(id);
        modelMapper.mapToUpdate(source, userToBeUpdated);
        repository.save(userToBeUpdated);
    }

    @Override
    public void addNewPrivateContactToUser(Long userId, Long addableUserId) throws UserNotFoundException, UserAlreadyInContactException, ContactFullException {
        User user = getById(userId);
        User addableUser = getById(addableUserId);
        PrivateContact contact = new PrivateContact();
        user.getContacts().add(contact);
        addableUser.getContacts().add(contact);
        repository.save(user);
        repository.save(addableUser);
    }

    @Override
    public void addMessageToContactByIdWithMessageType(Long userId, Long contactId, MessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException {
        if (messageTypeCode == 0) {
            TextMessage textMessage = new TextMessage();
            modelMapper.mapUsingParentClassProperties(addableMessageDto, textMessage);
            addMessageToContactById(userId, contactId, textMessage);
        }
    }

    @Override
    public void deleteContactById(Long userId, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound {
        User user = getById(userId);
        Contact contact = contactService.findById(contactId);
        Set<Contact> contacts = user.getContacts();
        if (Objects.isNull(contacts) || contacts.isEmpty()) {
            throw new UserHasNoContactException();
        }
        if (!contacts.remove(contact)) {
            throw new UnauthorizedAccessToContactException();
        }
        if (contact.getMembers().size() < 2) {
            contactService.deleteById(contactId);
        }
        repository.save(user);
    }

    @Override
    public UserProfileDto getUserProfile(Long id) throws UserNotFoundException {
        User foundUser = getById(id);
        return modelMapper.map(foundUser, UserProfileDto.class);
    }

    @Override
    public ContactDto findPrivateContactByUserIds(Long userId, Long searchedUsersId) throws UserNotFoundException, NoContactFound, ContactNotFound {
        User user = getById(userId);
        User searchedUser = getById(searchedUsersId);
        Contact foundContact = contactService.findPrivateContactByUsers(user, searchedUser);
        return modelMapper.map(foundContact, ContactDto.class);
    }

    @Override
    public PrivateContactResponseDto getPrivateContactResponseById(Long userId, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound {
        if (hasContact(userId, contactId)) {
            return contactService.getPrivateContactResponseById(userId, contactId);
        } else {
            throw new UnauthorizedAccessToContactException();
        }
    }

    private void addMessageToContactById(Long userId, Long contactId, Message addableMessage) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound {
        if (hasContact(userId, contactId)) {
            addableMessage.setSender(new Sender(userId, null));
            contactService.addMessageById(addableMessage, contactId);
        }
    }

    private User getById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    private boolean hasContact(Long userId, Long contactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException {
        User user = getById(userId);
        Contact contact = contactService.findById(contactId);
        if (!user.getContacts().contains(contact)) {
            throw new UnauthorizedAccessToContactException();
        }
        return true;
    }
}

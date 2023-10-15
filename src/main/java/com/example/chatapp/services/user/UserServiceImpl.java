package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.entities.contacts.GroupContact;
import com.example.chatapp.models.dto.contact.ContactDto;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.PrivateContact;
import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.messages.TextMessage;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.contact.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service("original")
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
        UserResponseDto userResponseDto = modelMapper.map(foundUser, UserResponseDto.class);
        contactService.setPrivateContactsDefaultNamesForResponse(id, userResponseDto.getContacts());
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
    public void makePrivateContact(Long requestingUsersId, Long addableUserId) throws UserNotFoundException, UserAlreadyInContactException, SameUserException {
        if (requestingUsersId.equals(addableUserId)) throw new SameUserException();
        User user = getById(requestingUsersId);
        User addableUser = getById(addableUserId);
        if (hasPrivateContact(user, addableUser)) throw new UserAlreadyInContactException();
        else {
            PrivateContact contact = new PrivateContact();
            user.getContacts().add(contact);
            addableUser.getContacts().add(contact);
            repository.save(user);
            repository.save(addableUser);
        }
    }

    @Override
    public void addUserToExistingGroupContact(Long userId, Long addableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, UserAlreadyInContactException, IllegalContactOperation, SameUserException {
        if(userId.equals(addableUserId)) throw new SameUserException();
        User user = getById(userId);
        User addableUser = getById(addableUserId);
        Contact contact = contactService.findById(groupContactId);
        if (!(contact instanceof GroupContact)) throw new IllegalContactOperation();
        if (isAdmin(user, (GroupContact) contact) && isContactMember(user, contact)) {
            if (!addableUser.getContacts().add(contact)) throw new UserAlreadyInContactException();
            repository.save(addableUser);
        }

    }

    @Override
    public void makeAdmin(Long userId, Long newAdminId, Long groupContactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException, IllegalContactOperation, UserAlreadyAdminInGroupContactException, SameUserException {
        if(userId.equals(newAdminId)) throw new SameUserException();
        User user = getById(userId);
        User newAdmin = getById(newAdminId);
        Contact contact = contactService.findById(groupContactId);
        if (!(contact instanceof GroupContact)) throw new IllegalContactOperation();
        if (isAdmin(user, (GroupContact) contact) && isContactMember(user, contact)) {
            Set<User> admins=((GroupContact) contact).getAdmins();
            if (admins.contains(newAdmin))
                throw new UserAlreadyAdminInGroupContactException();
            else
                admins.add(newAdmin);

        }

    }

    @Override
    public void removeFromGroupContact(Long userId, Long removeableUserId, Long groupContactId) throws UserNotFoundException, ContactNotFound, ContactFullException, IllegalContactOperation, UnauthorizedAccessToContactException {
        User user = getById(userId);
        User removeableUser = getById(removeableUserId);
        Contact contact = contactService.findById(groupContactId);
        if (!(contact instanceof GroupContact)) throw new ContactFullException();
        if (isAdmin(user, (GroupContact) contact) && isContactMember(user, contact)) {
            //todo:throw exception if it isn't found
            ((GroupContact) contact).getAdmins().remove(removeableUser);
            if (!removeableUser.getContacts().remove(contact))
                //todo:enum
                throw new UnauthorizedAccessToContactException("User doesn't belong to this contact!");
            repository.save(removeableUser);

        }
    }

    @Override
    public void makeGroupContact(Long userId, Set<Long> addableUserIds) throws UserNotFoundException, InsufficientContactMemberException {
        //if it exist in the list
        addableUserIds.remove(userId);
        if (Objects.isNull(addableUserIds) || addableUserIds.isEmpty()) throw new InsufficientContactMemberException();
        User user = getById(userId);
        GroupContact contact = new GroupContact();
        user.getContacts().add(contact);
        contact.getAdmins().add(user);
        for (Long id : addableUserIds) {
            User addableUser = getById(id);
            addableUser.getContacts().add(contact);
            repository.save(addableUser);
        }
        repository.save(user);
    }

    @Override
    public void addMessageToContactByIdWithMessageType(Long userId, Long contactId, SendingMessageDto addableMessageDto, int messageTypeCode) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException {
        //todo:implement enum
        if (messageTypeCode == 0) {
            User user = getById(userId);
            TextMessage textMessage = new TextMessage();
            modelMapper.mapUsingParentClassProperties(addableMessageDto, textMessage);
            addMessageToContactById(user, contactId, textMessage);
        }

    }

    @Override
    public void leaveGroupContact(Long userId, Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound {

        //todo:enum for storing 1 & 2
        //todo:
        User user = getById(userId);
        Contact contact = contactService.findById(contactId);
        Set<Contact> userContacts = user.getContacts();
        if (Objects.isNull(userContacts) || userContacts.isEmpty()) throw new UserHasNoContactException();
        if (isContactMember(user, contact)) {
            userContacts.remove(contact);
            if (contact.getMembers().size() < 2) contactService.deleteById(contactId);
            repository.save(user);
        }
    }

    @Override
    public UserProfileDto getUserProfile(Long id) throws UserNotFoundException {
        User foundUser = getById(id);
        return modelMapper.map(foundUser, UserProfileDto.class);

    }




    private boolean hasPrivateContact(User user1, User user2) {
        return user1 != null && user2 != null && user1.getContacts().stream().anyMatch(contact -> contact instanceof PrivateContact && contact.getMembers() != null && contact.getMembers().contains(user2));
    }

    private void addMessageToContactById(User user, Long contactId, Message addableMessage) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound {

        if (isContactMember(user, contactId)) {
            //   addableMessage.setSender(new Sender(userId,null));

            contactService.addMessageToContactById(addableMessage, user, contactId);
        }

    }


    private User getById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    private boolean isContactMember(User user, Long contactId) throws UserNotFoundException, ContactNotFound, UnauthorizedAccessToContactException {

        if (!existsInContact(user, contactId)) throw new UnauthorizedAccessToContactException();
        return true;
    }

    private boolean isContactMember(User user, Contact contact) throws UnauthorizedAccessToContactException {

        if (!contact.getMembers().contains(user)) throw new UnauthorizedAccessToContactException();
        return true;

    }

    private boolean isAdmin(User user, GroupContact contact) throws IllegalContactOperation {
        if (!contact.getAdmins().contains(user)) throw new IllegalContactOperation();
        return true;
    }

    private boolean checkIfUserAlreadyInContact(User user, Long contactId) throws ContactNotFound, UserAlreadyInContactException {
        if (existsInContact(user, contactId)) throw new UserAlreadyInContactException();
        return false;

    }

    private boolean existsInContact(User user, Long contactId) throws ContactNotFound {
        Contact contact = contactService.findById(contactId);
        return (Objects.nonNull(user) && user.getContacts().contains(contact));

    }

}
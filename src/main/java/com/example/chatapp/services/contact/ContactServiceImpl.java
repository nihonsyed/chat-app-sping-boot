package com.example.chatapp.services.contact;

import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.contacts.PrivateContact;
import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.dto.contact.PrivateContactResponseDto;
import com.example.chatapp.models.dto.message.MessageInContactResponseDto;
import com.example.chatapp.models.dto.user.PrivateContactMemberDto;
import com.example.chatapp.repositories.ContactRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository repository;

    @Autowired
    private CustomModelMapper modelMapper;

    @Override
    public List<Contact> findAll() throws NoContactFound {
        List<Contact> contactList = repository.findAll();
        if (contactList.isEmpty()) throw new NoContactFound();
        return contactList;
    }

    @Override
    public Contact findById(Long id) throws ContactNotFound {
        return repository.findById(id).orElseThrow(ContactNotFound::new);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addMessageToContactById(Message message, User user, Long contactId) throws ContactNotFound {
        Contact contact = findById(contactId);
        contact.addMessage(message);
        message.setContact(contact);
        message.setSender(user);
        repository.save(contact);
    }



    @Override
    public void setPrivateContactsDefaultNamesForResponse(Long userId, @NotNull Set<Contact> contacts) throws InsufficientContactMemberException {

        for (Contact contact : contacts) {
            if (contact instanceof PrivateContact) {
                setPrivateContactDefaultNameForUser(userId, contact);
            }
        }
    }

    @Override
    public List<Contact> findByType(String type) throws NoContactFound {
        List<Contact> contacts = findByType(type);
        if (Objects.isNull(contacts) || contacts.isEmpty())
            throw new NoContactFound();
        return contacts;
    }

    @Override
    public Contact findPrivateContactByUsers(User user, User searchedUser) throws NoContactFound, ContactNotFound {
        //todo:enum
        return findByType("private")
                .stream()
                .filter(c -> c.getMembers().containsAll(List.of(user, searchedUser)))
                .findFirst()
                .orElseThrow(ContactNotFound::new);
    }

    @Override
    public PrivateContactResponseDto getPrivateContactResponseById(Long userId,Long contactId) throws ContactNotFound {
        Contact contact=findById(contactId);

       setPrivateContactDefaultNameForUser(userId,contact);
       modelMapper.typeMap(User.class, PrivateContactMemberDto.class);
       modelMapper.typeMap(Message.class, MessageInContactResponseDto.class);
       return modelMapper.map(contact,PrivateContactResponseDto.class);

    }

    //todo:for group contact response

    //set nickname for private-contat & setting chat name for group chat
    public void updateNameById(Long checkableAdminId,Long contactId,String newName) throws ContactNotFound {
        //todo:authorize only group chat admins
        Contact contact=findById(contactId);
        contact.setName(newName);
        repository.save(contact);
    }
    private void setPrivateContactDefaultNameForUser(Long userId, Contact contact) {
        if (contact instanceof PrivateContact) {
            Set<User> members = contact.getMembers();
            members.stream().filter(user -> !user.getId().equals(userId)).findFirst().
                    ifPresentOrElse(otherUser -> contact.setName(otherUser.getFirstName()),
                            () -> {
                                //todo:enum
                                contact.setName("Chat Participant");
                            });

            //todo:also add last name
        }
        else
        {   //todo:set group name
            contact.setName("Group chat");
        }
    }


}

package com.example.chatapp.services.contact;

import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.models.contacts.Contact;
import com.example.chatapp.models.contacts.PrivateContact;
import com.example.chatapp.models.messages.Message;
import com.example.chatapp.models.users.User;
import com.example.chatapp.repositories.ContactRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository repository;

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
    public void addMessageById(Message message, Long contactId) throws ContactNotFound {
        Contact contact = findById(contactId);
        contact.addMessage(message);
        message.setContact(contact);
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


    private void setPrivateContactDefaultNameForUser(Long userId, Contact contact) throws InsufficientContactMemberException {
        Set<User> members = contact.getMembers();
        if (members.size() < 2) throw new InsufficientContactMemberException();
        members.stream().filter(user -> !user.getId().equals(userId)).findFirst().ifPresent(otherUser ->
                contact.setName(otherUser.getFirstName()));
    }

}

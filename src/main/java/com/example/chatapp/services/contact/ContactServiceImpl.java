package com.example.chatapp.services.contact;

import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.custom.mappers.CustomModelMapper;
import com.example.chatapp.models.contacts.Contact;
import com.example.chatapp.models.messages.Message;
import com.example.chatapp.repositories.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class ContactServiceImpl implements ContactService {


    @Autowired
    private CustomModelMapper modelMapper;

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
        Contact contact=findById(contactId);
        contact.addMessage(message);
        message.setContact(contact);
        repository.save(contact);
    }
}

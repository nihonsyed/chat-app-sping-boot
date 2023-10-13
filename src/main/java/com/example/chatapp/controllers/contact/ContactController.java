package com.example.chatapp.controllers.contact;

import com.example.chatapp.custom.exceptions.ContactNotFound;
import com.example.chatapp.custom.exceptions.NoContactFound;
import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.models.dto.contact.PrivateContactResponseDto;
import com.example.chatapp.services.contact.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contacts")

public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/all")
    @Operation(summary = "Get all contacts", description = "Retrieves a list of all contacts.")
    public ResponseEntity<Object> findAll() throws NoContactFound {
        List<Contact> contacts = contactService.findAll();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get contact by ID", description = "Retrieves a contact by its unique ID.")
    public ResponseEntity<Object> findById(@PathVariable("id") @Schema(example = "1") Long id) throws ContactNotFound {
        Contact contact = contactService.findById(id);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete contact by ID", description = "Deletes a contact by its unique ID.")
    public ResponseEntity<Object> deleteById(@PathVariable("id") @Schema(example = "1") Long id) {
        contactService.deleteById(id);
        return new ResponseEntity<>("Contact with ID " + id + " deleted successfully! ", HttpStatus.OK);
    }

    //todo:set name, remove member, add new member





}


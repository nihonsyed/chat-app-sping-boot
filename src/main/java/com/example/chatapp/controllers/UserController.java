package com.example.chatapp.controllers;

import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.dto.contact.ContactDto;
import com.example.chatapp.dto.user.UserRequestDto;
import com.example.chatapp.dto.user.UserResponseDto;
import com.example.chatapp.services.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")

//todo:implement a class for success responsebody
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account.")
    public ResponseEntity<?> save(@RequestBody UserRequestDto user) {
        userService.save(user);
        return new ResponseEntity<>("User registered successfully! ", HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get all users", description = "Retrieves a list of all registered users.")
    public ResponseEntity<?> findAll() throws NoUserFoundException {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID.")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws UserNotFoundException {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user by their unique ID.")
    public ResponseEntity<?> deleteById(@PathVariable("id") @Schema(example = "1") Long id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>("User with ID " + id + " deleted successfully! ", HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update user by ID", description = "Updates a user by their unique ID.")
    public ResponseEntity<?> updateById(@PathVariable("id") @Schema(example = "1") Long id,
                                        @RequestBody UserRequestDto user) throws UserNotFoundException, IllegalAccessException {
        userService.updateById(id,user);
        return new ResponseEntity<>("User with ID " + id + " updated successfully! ", HttpStatus.OK);
    }

   @PatchMapping(value = "{id}/contacts/add")
   @Operation(summary = "Add a new contact to a user", description = "Adds a new contact to a user")
   public ResponseEntity<?> addNewContactById(@PathVariable("id") @Schema(example = "1") Long userId,
                                              @RequestBody ContactDto newContact) throws UserNotFoundException, IllegalAccessException {
       userService.addNewPrivateContactToUser(userId,newContact);
       return new ResponseEntity<>("User with ID " + userId + " updated successfully! ", HttpStatus.OK);
   }



}

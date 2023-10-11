package com.example.chatapp.controllers;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.dto.message.MessageDto;
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
//todo:implement enum for responsebody message
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account.")
    public ResponseEntity<Object> save(@RequestBody UserRequestDto user) {
        userService.save(user);
        return new ResponseEntity<>("User registered successfully! ", HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Get all users", description = "Retrieves a list of all registered users.")
    public ResponseEntity<Object> findAll() throws NoUserFoundException {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID.")
    public ResponseEntity<Object> findById(@PathVariable("id") @Schema(example = "1") Long id) throws UserNotFoundException, InsufficientContactMemberException {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a user by their unique ID.")
    public ResponseEntity<Object> deleteById(@PathVariable("id") @Schema(example = "1") Long id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>("User with ID " + id + " deleted successfully! ", HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update user by ID", description = "Updates a user by their unique ID.")
    public ResponseEntity<Object> updateById(@PathVariable("id") @Schema(example = "1") Long id,
                                        @RequestBody UserRequestDto user) throws UserNotFoundException, IllegalAccessException {
        userService.updateById(id,user);
        return new ResponseEntity<>("User with ID " + id + " updated successfully! ", HttpStatus.OK);
    }

   @PatchMapping(value = "{id}/contacts/private/add/{addableUserId}")
   @Operation(summary = "Add a new contact to a user", description = "Adds a new  private-contact to a user")
   public ResponseEntity<Object> addNewContactById(@PathVariable("id") @Schema(example = "1") Long userId,
                                              @PathVariable("addableUserId")  @Schema(example = "2") Long addableUserId) throws UserNotFoundException, IllegalAccessException, UserAlreadyInContactException, ContactFullException {
       userService.addNewPrivateContactToUser(userId,addableUserId);
       return new ResponseEntity<>("User with ID " + userId + " updated successfully! ", HttpStatus.OK);
   }

   @PatchMapping(value = "{id}/contacts/{contactId}/text/send")
    @Operation(summary = "Send a new message",description = "send a new message to a private or group chat")
    public ResponseEntity<Object> sendTextMessage(@PathVariable("id")  @Schema(example = "1") Long userId,@PathVariable("contactId")  @Schema(example = "1") Long contactId,@RequestBody MessageDto newMessage) throws UserNotFoundException, UnauthorizedContactAccessException, ContactNotFound, IllegalAccessException {
       userService.addMessageToContactByIdWithMessageType(userId,contactId,newMessage,0);
       return new ResponseEntity<>("Message sent successfully!",HttpStatus.CREATED);
     }

}

package com.example.chatapp.controllers.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.models.dto.contact.ContactDto;
import com.example.chatapp.models.dto.message.MessageDto;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserContactController {

    @Autowired
    private UserService userService;
    @PatchMapping(value = "{id}/contacts/private/add/{addableUserId}")
    @Operation(summary = "Add a new contact to a user", description = "Adds a new  private-contact to a user")
    public ResponseEntity<Object> addNewContactById(@PathVariable("id") @Schema(example = "1") Long userId,
                                                    @PathVariable("addableUserId")  @Schema(example = "2") Long addableUserId) throws UserNotFoundException, IllegalAccessException, UserAlreadyInContactException, ContactFullException {
        userService.addNewPrivateContactToUser(userId,addableUserId);
        return new ResponseEntity<>("User with ID " + userId + " updated successfully! ", HttpStatus.OK);
    }

    @PatchMapping(value = "{id}/contacts/{contactId}/text/send")
    @Operation(summary = "Send a new message",description = "send a new message to a private or group chat")
    public ResponseEntity<Object> sendTextMessage(@PathVariable("id")  @Schema(example = "1") Long userId,@PathVariable("contactId")  @Schema(example = "1") Long contactId,@RequestBody MessageDto newMessage) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, IllegalAccessException {
        userService.addMessageToContactByIdWithMessageType(userId,contactId,newMessage,0);
        return new ResponseEntity<>("Message sent successfully!",HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{userId}/contacts/{contactId}")
    @Operation(summary = "Delete a contact by ID", description = "Deletes a contact by their unique ID.")
    public ResponseEntity<Object> deleteContactById(@PathVariable("userId") @Schema(example = "1") Long userId, @PathVariable("contactId") @Schema(example = "1") Long contactId) throws UserNotFoundException, UnauthorizedAccessToContactException, ContactNotFound, NoContactFound {
        userService.deleteContactById(userId, contactId);
        return new ResponseEntity<>("Contact with ID " + contactId + " deleted successfully for user with ID " + userId, HttpStatus.OK);
    }
    @GetMapping(value = "/profile/{id}")
    @Operation(summary = "Get user profile by ID", description = "Retrieves a user's profile by their unique ID.")
    public ResponseEntity<Object> getUserProfile(@PathVariable("id") @Schema(example = "1") Long id) throws UserNotFoundException {
        UserProfileDto userProfile = userService.getUserProfile(id);
        return ResponseEntity.ok(userProfile);
    }
    @GetMapping(value = "/contacts/private")
    @Operation(summary = "Find private contact by User IDs", description = "Finds a private contact based on the User IDs.")
    public ResponseEntity<Object> findPrivateContactByUserIds(
            @RequestParam("userId") @Schema(example = "1") Long userId,
            @RequestParam("searchedUsersId") @Schema(example = "2") Long searchedUsersId
    ) throws UserNotFoundException, NoContactFound, ContactNotFound {
        ContactDto privateContact= userService.findPrivateContactByUserIds(userId,searchedUsersId);
        return ResponseEntity.ok(privateContact);
    }

}

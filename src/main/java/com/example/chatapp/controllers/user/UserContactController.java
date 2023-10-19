package com.example.chatapp.controllers.user;

import com.example.chatapp.custom.exceptions.*;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import com.example.chatapp.services.user.UserContactSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping(value = "testing/users")
public class UserContactController {

    @Autowired
    private UserContactSerivce userContactSerivce;

    @PatchMapping(value = "{id}/contacts/private/add/{addableUserId}")
    @Operation(summary = "Make a privte-contact between two users", description = "Adds a new  private-contact to a user")
    public ResponseEntity<Object> makePrivateContact(@PathVariable("id") @Schema(example = "1", description = "requesting user's id") Long requestingUsersId, @PathVariable("addableUserId") @Schema(example = "2", description = "addable user's id") Long addableUserId) throws UserNotFoundException, InsufficientContactMemberException, IllegalContactOperation {
        userContactSerivce.makePrivateContact(requestingUsersId, addableUserId);
        return new ResponseEntity<>("User with ID " + addableUserId + " has been added!", HttpStatus.OK);
    }

    @PatchMapping(value = "{id}/contacts/{contactId}/text/send")
    @Operation(summary = "Send a new text message", description = "send a new message to a private or group chat")
    public ResponseEntity<Object> sendTextMessage(@PathVariable("id") @Schema(example = "1") Long userId, @PathVariable("contactId") @Schema(example = "1") Long contactId, @RequestBody SendingMessageDto newMessage) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, IllegalAccessException, MessageSendingFailureException {
        userContactSerivce.sendMessage(userId, contactId, newMessage, 0);
        return new ResponseEntity<>("Message sent successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{userId}/contacts/leave/{contactId}")
    @Operation(summary = "leave a contact", description = "Deletes a contact by their unique ID.")
    public ResponseEntity<Object> leavePrivateContact(@PathVariable("userId") @Schema(example = "1") Long userId, @PathVariable("contactId") @Schema(example = "1") Long contactId) throws UserNotFoundException, UserIsNotInContactException, ContactNotFound, NoContactFound {
        userContactSerivce.leaveContact(userId, contactId);
        return new ResponseEntity<>("Contact with ID " + contactId + " deleted successfully for user with ID " + userId, HttpStatus.OK);
    }


//    @GetMapping(value = "{userId}/response/{id}")
//    @Operation(summary = "Get contact-response", description = "Retrieves a contact-response by its unique ID.")
//    public ResponseEntity<Object> getContactResponseById(
//            @PathVariable("userId") @Schema(example = "1") Long userId
//            ,@PathVariable("id") @Schema(example = "1") Long contactId) throws ContactNotFound {
//        PrivateContactResponseDto contactResponseDto = contactService.getPrivateContactResponseById(userId,contactId);
//        return ResponseEntity.ok(contactResponseDto);
//    }

    @PatchMapping(value = "{id}/contacts/group/add")
    @Operation(summary = "Make a new group contact", description = "a group contact")
    public ResponseEntity<Object> makeGroupContact(@PathVariable("id") @Schema(example = "1") Long requestingUserId, @RequestBody Set<Long> addableUserIds) throws UserNotFoundException, InsufficientContactMemberException, ContactFullException {
        userContactSerivce.makeGroupContact(requestingUserId, addableUserIds);
        return new ResponseEntity<>("Group has successfully been made " + requestingUserId, HttpStatus.OK);
    }

    @PatchMapping(value = "{id}/contacts/group/{groupContactId}/add-user/{addableUserId}")
    @Operation(summary = "Add a user to an existing contact (only group contact)", description = "Adds a user to an existing group contact")
    public ResponseEntity<Object> addToExistingGroupContact(@PathVariable("id") @Schema(example = "1") Long userId, @PathVariable("groupContactId") @Schema(example = "5") Long groupContactId, @PathVariable("addableUserId") @Schema(example = "3") Long addableUserId) throws UserNotFoundException, ContactNotFound, UserIsNotInContactException,   IllegalContactOperation {
        userContactSerivce.addMember(userId, addableUserId, groupContactId);
        return new ResponseEntity<>("User with ID " + addableUserId + " added to the group contact with ID " + groupContactId, HttpStatus.OK);
    }

    @PatchMapping(value = "{id}/contacts/group/{groupContactId}/makeAdmin/{newAdminId}")
    @Operation(summary = "Make a user admin (only group contact) ", description = "Makes a user an admin in a group contact")
    public ResponseEntity<Object> makeAdminInGroupContact(@PathVariable("id") @Schema(example = "1") Long userId, @PathVariable("newAdminId") @Schema(example = "5") Long newAdminId, @PathVariable("groupContactId") @Schema(example = "3") Long groupContactId) throws UserNotFoundException, ContactNotFound, UserIsNotInContactException, IllegalContactOperation {
        userContactSerivce.makeAdmin(userId, newAdminId, groupContactId);
        return new ResponseEntity<>("User with ID " + newAdminId + " made admin in the group contact with ID " + groupContactId, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/contacts/group/remove/{removeableUserId}/{groupContactId}")
    @Operation(summary = "Remove a user from a group contact", description = "Remove a user from the group contact")
    public ResponseEntity<Object> removeFromGroupContact(@PathVariable("userId") @Schema(example = "3", description = "client id") Long userId, @PathVariable("removeableUserId") @Schema(example = "4") Long removeableUserId, @PathVariable("groupContactId") @Schema(example = "5") Long groupContactId) throws UserNotFoundException, ContactNotFound, ContactFullException, IllegalContactOperation, UserIsNotInContactException {
        userContactSerivce.removeMember(userId, removeableUserId, groupContactId);
        return new ResponseEntity<>("User removed from the group contact successfully!", HttpStatus.OK);
    }
}

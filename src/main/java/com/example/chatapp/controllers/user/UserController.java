package com.example.chatapp.controllers.user;

import com.example.chatapp.custom.exceptions.InsufficientContactMemberException;
import com.example.chatapp.custom.exceptions.NoUserFoundException;
import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.models.dto.user.UserProfileDto;
import com.example.chatapp.models.dto.user.UserRequestDto;
import com.example.chatapp.models.dto.user.UserResponseDto;
import com.example.chatapp.services.user.UserService;
import com.example.chatapp.services.user.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private UserServiceImpl userService;

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

    @GetMapping(value = "/profile/{id}")
    @Operation(summary = "Get user profile by ID", description = "Retrieves a user's profile by their unique ID.")
    public ResponseEntity<Object> getUserProfile(@PathVariable("id") @Schema(example = "1") Long id) throws UserNotFoundException {
        UserProfileDto userProfile = userService.getUserProfile(id);
        return ResponseEntity.ok(userProfile);
    }

}

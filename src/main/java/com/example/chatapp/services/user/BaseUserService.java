package com.example.chatapp.services.user;

import com.example.chatapp.custom.exceptions.UserNotFoundException;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.repositories.UserRepository;

public interface BaseUserService {

    default User getById(UserRepository repository, Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}

package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.user.UserCreateInputDTO;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.exceptions.UserValidation;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User verifyUserCreation(UserCreateInputDTO data) {
        if(repository.existsByDocument(data.document())){
            throw new UserValidation("Already exists a user with that document.");
        }

        if(repository.existsByEmail(data.email())){
            throw new UserValidation("Already exists a user with that email.");
        }

        User user = new User(data);
        repository.save(user);

        return user;
    }
}

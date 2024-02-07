package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.UserCreateInputDTO;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User verifyUserCreation(UserCreateInputDTO data) {
        if(!repository.existsByDocument(data.document()) && !repository.existsByEmail(data.email())){
            User user = new User(data);
            repository.save(user);

            return user;
        }
        return null;
    }
}

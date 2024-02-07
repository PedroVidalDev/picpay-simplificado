package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.UsuarioInputDTO;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UserRepository repository;

    public User verifyUserCreation(UsuarioInputDTO data) {
        if(!repository.existsByDocument(data.document()) && !repository.existsByEmail(data.email())){
            User user = new User(data);
            repository.save(user);

            return user;
        }
        return null;
    }
}

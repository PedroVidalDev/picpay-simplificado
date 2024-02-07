package com.pedro.picpay.application.dtos.user;

import com.pedro.picpay.infrastructure.entities.User;

public record UserOutputDTO(Long id, String name, String document, String email) {
    public UserOutputDTO(User user){
        this(user.getId(), user.getName(), user.getDocument(), user.getEmail());
    }
}

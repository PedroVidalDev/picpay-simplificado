package com.pedro.picpay.infrastructure.repositories;

import com.pedro.picpay.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDocument(String document);

    boolean existsByEmail(String email);
}

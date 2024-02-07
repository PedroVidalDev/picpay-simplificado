package com.pedro.picpay.infrastructure.entities;

import com.pedro.picpay.application.dtos.user.UserCreateInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="User")
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String document;
    private String email;
    private String password;

    public User(UserCreateInputDTO data){
        this.id = null;
        this.name = data.name();
        this.document = data.document();
        this.email = data.email();
        this.password = data.password();
    }
}

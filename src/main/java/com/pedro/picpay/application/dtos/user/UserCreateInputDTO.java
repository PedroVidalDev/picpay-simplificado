package com.pedro.picpay.application.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record UserCreateInputDTO(
        @NotBlank String name,
        @NotBlank @CPF String document,
        @NotBlank @Email String email,
        @NotBlank String password){
}

package com.pedro.picpay.application;

import com.pedro.picpay.application.dtos.UsuarioInputDTO;
import com.pedro.picpay.domain.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UsuarioInputDTO data){
        var user = service.verifyUserCreation(data);

        return ResponseEntity.ok().build();
    }

}

package com.pedro.picpay.application;

import com.pedro.picpay.application.dtos.UserCreateInputDTO;
import com.pedro.picpay.domain.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserCreateInputDTO data){
        var user = service.verifyUserCreation(data);

        return ResponseEntity.ok().build();
    }

}

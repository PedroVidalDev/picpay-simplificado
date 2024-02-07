package com.pedro.picpay.application;

import com.pedro.picpay.application.dtos.user.UserCreateInputDTO;
import com.pedro.picpay.application.dtos.user.UserOutputDTO;
import com.pedro.picpay.domain.UserService;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserCreateInputDTO data, UriComponentsBuilder uriBuild){
        User user = service.verifyUserCreation(data);

        var uri = uriBuild.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserOutputDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable String id){
        repository.deleteById(Long.parseLong(id));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable String id){
        var user = repository.getReferenceById(Long.parseLong(id));

        return ResponseEntity.ok(new UserOutputDTO(user));
    }

}

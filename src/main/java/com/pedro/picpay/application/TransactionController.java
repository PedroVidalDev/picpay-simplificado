package com.pedro.picpay.application;

import com.pedro.picpay.application.dtos.transaction.TransactionInputDTO;
import com.pedro.picpay.application.dtos.transaction.TransactionOutputDTO;
import com.pedro.picpay.application.dtos.user.UserOutputDTO;
import com.pedro.picpay.domain.TransactionService;
import com.pedro.picpay.infrastructure.entities.Transaction;
import com.pedro.picpay.infrastructure.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ReflectPermission;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private TransactionRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createTransaction(@RequestBody @Valid TransactionInputDTO data, UriComponentsBuilder uriBuild){

        Transaction transaction = service.verifyTransaction(data);

        var uri = uriBuild.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransactionOutputDTO(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable String id){
        var transaction = repository.getReferenceById(Long.parseLong(id));

        return ResponseEntity.ok(new TransactionOutputDTO(transaction));
    }
}

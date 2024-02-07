package com.pedro.picpay.application;

import com.pedro.picpay.application.dtos.transaction.TransactionInputDTO;
import com.pedro.picpay.domain.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.ReflectPermission;

@RestController
@RequestMapping("/transations")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    @Transactional
    public ResponseEntity createTransaction(@RequestBody @Valid TransactionInputDTO data){

        service.verifyTransaction(data);

        return ResponseEntity.ok().build();
    }
}

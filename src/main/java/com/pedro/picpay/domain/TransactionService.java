package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.transaction.TransactionInputDTO;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.repositories.TransactionRepository;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    public void verifyTransaction(TransactionInputDTO data){
        var payer = userRepository.findById(data.payer());
        var payee = userRepository.findById(data.payee());


    }
}

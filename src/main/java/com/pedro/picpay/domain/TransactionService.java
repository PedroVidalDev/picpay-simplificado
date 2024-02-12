package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.transaction.TransactionInputDTO;
import com.pedro.picpay.application.dtos.transaction.TransactionOutputDTO;
import com.pedro.picpay.infrastructure.entities.Transaction;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.exceptions.TransactionValidation;
import com.pedro.picpay.infrastructure.repositories.TransactionRepository;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAttribute;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;
    public Transaction verifyTransaction(TransactionInputDTO data){
        var payer = userRepository.getReferenceById(data.payer());
        var payee = userRepository.getReferenceById(data.payee());

        if(userService.isCnpj(userService.formatData(payer.getDocument()))){
            throw new TransactionValidation("That payer is not a normal user and cant send cash to anyone.");
        }

        if(payer.getValue() < data.value()){
            throw new TransactionValidation("Payer doesnt have the enough money.");
        }

        if(data.value() < 0){
            throw new TransactionValidation("Transaction value lower than zero.");
        }

        payee.actualizeValue(data.value());
        payer.actualizeValue(data.value() * -1);

        Transaction transaction = new Transaction(null, payer, payee, data.value());

        transactionRepository.save(transaction);

        return transaction;

    }
}

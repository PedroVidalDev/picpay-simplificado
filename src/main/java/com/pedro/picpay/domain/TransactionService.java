package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.transaction.TransactionInputDTO;
import com.pedro.picpay.application.dtos.transaction.TransactionOutputDTO;
import com.pedro.picpay.infrastructure.entities.Transaction;
import com.pedro.picpay.infrastructure.entities.User;
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
    public TransactionOutputDTO verifyTransaction(TransactionInputDTO data){
        var payer = userRepository.getReferenceById(data.payer());
        var payee = userRepository.getReferenceById(data.payee());

        if(payer.getValue() < data.value()){
            throw new RuntimeException("pagador nao tem dinheiro suficiente");
        } else{
            payee.actualizeValue(data.value());
            payer.actualizeValue(data.value() * -1);

            Transaction transaction = new Transaction(null, payer, payee, data.value());

            return new TransactionOutputDTO(transaction);
        }
    }
}

package com.pedro.picpay.application.dtos.transaction;

import com.pedro.picpay.application.dtos.user.UserOutputDTO;
import com.pedro.picpay.infrastructure.entities.Transaction;
import com.pedro.picpay.infrastructure.entities.User;

public record TransactionOutputDTO(Long id, UserOutputDTO payer, UserOutputDTO payee, float value) {
    public TransactionOutputDTO(Transaction data){
        this(data.getId(), new UserOutputDTO(data.getPayer()), new UserOutputDTO(data.getPayee()), data.getValue());
    }
}

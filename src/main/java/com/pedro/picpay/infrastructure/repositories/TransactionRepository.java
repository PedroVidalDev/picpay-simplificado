package com.pedro.picpay.infrastructure.repositories;

import com.pedro.picpay.infrastructure.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

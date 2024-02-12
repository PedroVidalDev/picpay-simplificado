package com.pedro.picpay.infrastructure.exceptions;

import com.pedro.picpay.application.dtos.message.MessageOutputDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TransactionValidation.class)
    public ResponseEntity transactionValidation(TransactionValidation e){

        return ResponseEntity.badRequest().body(new MessageOutputDTO(e.getMessage()));
    }

    @ExceptionHandler(UserValidation.class)
    public ResponseEntity userValidation(UserValidation e){
        return ResponseEntity.badRequest().body(new MessageOutputDTO(e.getMessage()));
    }
}

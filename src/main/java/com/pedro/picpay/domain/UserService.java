package com.pedro.picpay.domain;

import com.pedro.picpay.application.dtos.user.UserCreateInputDTO;
import com.pedro.picpay.infrastructure.entities.User;
import com.pedro.picpay.infrastructure.exceptions.UserValidation;
import com.pedro.picpay.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User verifyUserCreation(UserCreateInputDTO data) {
        String document = formatData(data.document());

        if(isCpf(document)){
            isCpfValid(data.document().toCharArray());
        };

        if(repository.existsByDocument(document)){
            throw new UserValidation("Already exists a user with that document.");
        }

        if(repository.existsByEmail(data.email())){
            throw new UserValidation("Already exists a user with that email.");
        }

        User user = new User(data);
        repository.save(user);

        return user;
    }

    public String formatData(String data){
        data = data.replaceAll("\\.", "");
        data = data.replaceAll("-", "");

        return data;
    }

    public boolean isCpf(String cpf){

        char[] list = cpf.toCharArray();

        if(list.length != 11){
            return false;
        }

        return true;

    }

    public void isCpfValid(char[] list){

        int firstResult = 0;
        int secondResult = 0;

        int firstCounter = 10;
        int secondCounter = 11;

        for(char number : list) {
            if(firstCounter < 2){
                break;
            }

            firstResult = firstResult + (Integer.parseInt(String.valueOf(number)) * firstCounter);
            firstCounter--;
        }

        for(char number : list) {
            if(secondCounter < 2){
                break;
            }

            secondResult = secondResult + (Integer.parseInt(String.valueOf(number)) * secondCounter);
            secondCounter--;
        }

        if((firstResult * 10) % 11 != Integer.parseInt(String.valueOf(list[9])) || (secondResult * 10) % 11 != Integer.parseInt(String.valueOf(list[10]))){
            throw new UserValidation("Invalid CPF.");
        }
    }
}

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
            isCpfValid(document.toCharArray());
        };

        if(isCnpj(document)){
            isCnpjValid(document.toCharArray());
        }

        if(!isCpf(document) && !isCnpj(document)){
            throw new UserValidation("Document is not valid.");
        }

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
        data = data.replaceAll("/", "");

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

        int counter = 10;

        for(char number : list) {
            if(counter < 2){
                break;
            }

            firstResult = firstResult + (Integer.parseInt(String.valueOf(number)) * counter);
            counter--;
        }

        counter = 11;

        for(char number : list) {
            if(counter < 2){
                break;
            }

            secondResult = secondResult + (Integer.parseInt(String.valueOf(number)) * counter);
            counter--;
        }

        int firstDigit = (firstResult * 10) % 11 == 10 ? 0 : (firstResult * 10) % 11;
        int secondDigit = (secondResult * 10) % 11 == 10 ? 0 : (secondResult * 10) % 11;

        if(firstDigit != Integer.parseInt(String.valueOf(list[9])) || secondDigit != Integer.parseInt(String.valueOf(list[10]))){
            throw new UserValidation("Invalid CPF.");
        }
    }

    public boolean isCnpj(String cnpj){
        char[] list = cnpj.toCharArray();

        if(list.length != 14){
            return false;
        }

        return true;
    }

    private void isCnpjValid(char[] list) {
        int firstResult = 0;
        int secondResult = 0;

        int firstCounter = 5;
        int secondCounter = 9;

        for(char number : list) {
            if(firstCounter < 2){
                if(secondCounter < 2){
                    break;
                }

                firstResult = firstResult + (Integer.parseInt(String.valueOf(number)) * secondCounter);
                secondCounter--;
            }

            else{
                firstResult = firstResult + (Integer.parseInt(String.valueOf(number)) * firstCounter);
                firstCounter--;
            }
        }

        firstCounter = 6;
        secondCounter = 9;

        for(char number : list) {
            if(firstCounter < 2){
                if(secondCounter < 2){
                    break;
                }

                secondResult = secondResult + (Integer.parseInt(String.valueOf(number)) * secondCounter);
                secondCounter--;
            }

            else{
                secondResult = secondResult + (Integer.parseInt(String.valueOf(number)) * firstCounter);
                firstCounter--;
            }
        }

        int firstDigit = firstResult % 11 < 2 ? 0 : 11 - firstResult % 11;
        int secondDigit = secondResult % 11 < 2 ? 0 : 11 - secondResult % 11;

        if(firstDigit != Integer.parseInt(String.valueOf(list[12])) || secondDigit != Integer.parseInt(String.valueOf(list[13]))){
            throw new UserValidation("Invalid CNPJ.");
        }
    }

}

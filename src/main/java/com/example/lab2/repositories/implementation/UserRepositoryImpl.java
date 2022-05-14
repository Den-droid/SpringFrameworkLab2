package com.example.lab2.repositories.implementation;

import com.example.lab2.models.Administrator;
import com.example.lab2.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Administrator getByLoginPassword(String login, String password) {
        return new Administrator("Denis", "Yaremchuk", "Film manager");
    }
}

package com.example.lab2.repositories.interfaces;

import com.example.lab2.models.Administrator;

public interface UserRepository {
    Administrator getByLoginPassword(String login, String password);
}

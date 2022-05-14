package com.example.lab2.services.interfaces;

import com.example.lab2.models.Administrator;

public interface UserService {
    Administrator login(String login, String password);
    void logout();
}

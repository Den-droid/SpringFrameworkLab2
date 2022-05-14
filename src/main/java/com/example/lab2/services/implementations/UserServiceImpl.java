package com.example.lab2.services.implementations;

import com.example.lab2.models.Administrator;
import com.example.lab2.repositories.interfaces.UserRepository;
import com.example.lab2.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Administrator login(String login, String password) {
        return this.userRepository.getByLoginPassword(login, password);
    }

    @Override
    public void logout() {

    }
}

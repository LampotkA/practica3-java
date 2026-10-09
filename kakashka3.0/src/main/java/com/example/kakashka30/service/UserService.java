package com.example.kakashka30.service;

import com.example.kakashka30.model.User;
import com.example.kakashka30.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Сохранение нового пользователя
    public User register(User user) {
        return userRepository.save(user);
    }

    // Проверка логина и пароля по базе
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}

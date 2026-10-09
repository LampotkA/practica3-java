package com.example.kakashka30.controller;

import com.example.kakashka30.model.User;
import com.example.kakashka30.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Форма входа
    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    // Проверка логина и пароля, сохранение флага в сессии
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if (userService.authenticate(username, password)) {
            session.setAttribute("user", username);
            return "redirect:/students";
        }
        model.addAttribute("error", "Неверный логин или пароль");
        return "login-form";
    }

    // Форма регистрации
    @GetMapping("/register")
    public String registerForm() {
        return "register-form";
    }

    // Сохранение пользователя
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {
        userService.register(new User(username, password));
        return "redirect:/login";
    }

    // Очистка сессии
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

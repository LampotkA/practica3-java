package com.example.kakashka30.controller;

import com.example.kakashka30.model.Student;
import com.example.kakashka30.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Список студентов (только для вошедших пользователей)
    @GetMapping
    public String list(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("user", session.getAttribute("user"));
        return "students-list";
    }

    // Форма добавления (только для вошедших пользователей)
    @GetMapping("/add")
    public String addForm(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("student", new Student());
        return "students-form";
    }

    // Сохранение студента и редирект на список
    @PostMapping("/add")
    public String add(Student student, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }
}

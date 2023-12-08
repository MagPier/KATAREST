package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    public UserController(
            UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUserInfo(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;
        model.addAttribute("user", user);
        return "user";
    }
}

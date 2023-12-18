package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

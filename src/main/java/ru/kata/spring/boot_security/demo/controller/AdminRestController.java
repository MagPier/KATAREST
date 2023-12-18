package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> pageEdit(@PathVariable("id") long id,
                                      @RequestBody User user
    ) {

        String oldPassword = userService.getById(id).getPassword();
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(oldPassword);
            userService.update(user);
        } else {
            userService.save(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

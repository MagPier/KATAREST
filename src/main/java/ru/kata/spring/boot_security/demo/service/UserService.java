package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
public interface UserService {

    List<User> findAll ();
    User getById(long id);
    void save(User user);
    void deleteById(long id);
    User findByUsername(String username);
    void update(User user);
    User passwordCoder(User user);
}

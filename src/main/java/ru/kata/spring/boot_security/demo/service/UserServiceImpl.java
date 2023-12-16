package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDAO, RoleDao roleDAO, PasswordEncoder passwordEncoder) {
        this.userDao = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(long id) {
        User user = null;
        Optional<User> optional = userDao.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(passwordCoder(user));
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


}

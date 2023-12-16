package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDAO) {
        this.userDao = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return userDao.findByUsername(s);
        } catch (UsernameNotFoundException u) {
            throw new UsernameNotFoundException("user not found");
        }
    }
}

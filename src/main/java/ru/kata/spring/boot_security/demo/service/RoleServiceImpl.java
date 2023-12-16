package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> findAllRole() {
        return roleDAO.findAll();
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return new HashSet<>(roleDAO.findAllById(roles));
    }
}

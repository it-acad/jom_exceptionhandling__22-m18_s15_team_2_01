package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (role != null) {
            return roleRepository.save(role);
        }
        throw new NullEntityReferenceException("You can't create null role!");
    }

    @Override
    public Role readById(long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("You are searching for not existing role with id=" + id + "!");
    }

    @Override
    public Role update(Role role) {
        if (role == null) {
            throw new NullEntityReferenceException("You can't update null role!'");
        }
        if (readById(role.getId()) == null) {
            throw new EntityNotFoundException("You can't update not existing role!");
        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
        if (role != null) {
            roleRepository.delete(role);
        }
        throw new EntityNotFoundException("You can't delete role with not existing id=" + id + "!");
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}

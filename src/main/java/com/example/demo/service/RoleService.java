package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAll()
    {
        return roleRepository.findAll();
    }

    public Role getById(long id)
    {
        Optional<Role> role= roleRepository.findById(id);
        return role.isPresent()? role.get(): null;
    }

    public Role save(Role role)
    {
       return roleRepository.save(role);
    }

    public void delete(Role role)
    {
        roleRepository.delete(role);
    }


}

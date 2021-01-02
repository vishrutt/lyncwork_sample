package com.example.demo.controller;

import com.example.demo.customexceptions.NameNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping({"/roles","/Roles"})
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Role findById(@PathVariable long id) {
        return roleService.getById(id);
    }

    @GetMapping(value= "/", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Collection<Role> findAll() {
        return roleService.getAll();
    }

    @PostMapping(value= "/",  consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Role insert(@RequestBody final Role role)
    {
        if(StringUtils.isEmpty(role.getName()))
        {
            throw new NameNotFoundException("{role.name: Name is mandatory}");
        }
        return roleService.save(role);
    }

    @PutMapping(value= "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Role update(@PathVariable("id") final long id, @RequestBody final Role role)
    {
        role.setId(id);
        role.setUsers(null);
        return roleService.save(role);
    }

    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Role delete(@PathVariable("id") final long id, @RequestBody final Role role)
    {
        roleService.delete(role);
        return role;
    }

}

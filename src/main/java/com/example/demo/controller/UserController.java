package com.example.demo.controller;

import com.example.demo.customexceptions.NameNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping({"/user","/User"})
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public User findById(@PathVariable long id) {
        return userService.getById(id);
    }

    @GetMapping(value= "/", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Collection<User> findAll() {
        return userService.getAll();
    }

    @PostMapping(value= "/",  consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public User insert(@RequestBody final User user)
    {
        if(StringUtils.isEmpty(user.getFirstName()))
        {
            throw new NameNotFoundException("{firstName: First name is mandatory}");
        }
        int i=0;
        for(Role role:user.getRoles())
        {
            if(StringUtils.isEmpty(role.getName()))
            {
                throw new NameNotFoundException("{roles["+i+"].name: Name is mandatory}");
            }
            i++;
        }
        return userService.save(user);
    }

    @PutMapping(value= "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public User update(@PathVariable("id") final long id, @RequestBody final User user)
    {
        user.setId(id);
        user.setRoles(null);
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public User delete(@PathVariable("id") final long id, @RequestBody final User user)
    {
        userService.delete(user);
        return user;
    }
}

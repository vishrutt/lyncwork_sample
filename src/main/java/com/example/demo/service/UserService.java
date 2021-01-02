package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User getById(long id)
    {
        Optional<User> user= userRepository.findById(id);
        return user.isPresent()? user.get(): null;
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public void delete(long id)
    {
        userRepository.deleteById(id);
    }

}

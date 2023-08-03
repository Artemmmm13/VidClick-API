package com.api.vidclick.vidclick.controllers;

import com.api.vidclick.vidclick.models.User;
import com.api.vidclick.vidclick.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return repository.findAll();
    }

}

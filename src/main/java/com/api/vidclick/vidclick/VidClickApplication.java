package com.api.vidclick.vidclick;

import com.api.vidclick.vidclick.models.User;
import com.api.vidclick.vidclick.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/user")
public class VidClickApplication {

    private final UserRepository repository;

    public VidClickApplication(UserRepository repository) {
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


    public static void main(String[] args) {
        SpringApplication.run(VidClickApplication.class, args);
	}

}

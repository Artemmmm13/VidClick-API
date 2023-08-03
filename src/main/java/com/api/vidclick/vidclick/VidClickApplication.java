package com.api.vidclick.vidclick;

import com.api.vidclick.vidclick.models.User;
import com.api.vidclick.vidclick.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class VidClickApplication {
    public static void main(String[] args) {
        SpringApplication.run(VidClickApplication.class, args);
	}

}

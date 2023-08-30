package com.api.vidclick.controllers;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.ErrorMessageResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.services.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginAndSignupController {
    private final AuthenticationService authService;

    public LoginAndSignupController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    private ResponseEntity<?> registerUser(@RequestBody RegisterRequest request, HttpServletResponse response){
        try {
            return authService.register(request, response);
        } catch (IllegalArgumentException e) {
           return ResponseEntity.status(400).body(new ErrorMessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    private ResponseEntity<?> authenticateRequest(@RequestBody AuthenticationRequest request, HttpServletResponse response){
        try{
            return authService.authenticate(request, response);
        } catch (Exception e){
            return ResponseEntity.status(400).body(new ErrorMessageResponse(e.getMessage()));
        }
    }
}

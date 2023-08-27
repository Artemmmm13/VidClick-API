package com.api.vidclick.controllers;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.SignUpResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.services.AuthenticationService;
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
    private ResponseEntity<SignUpResponse> registerUser(@RequestBody RegisterRequest request){
        try {
            return authService.register(request);
        } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    private ResponseEntity<SignUpResponse> authenticateRequest(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

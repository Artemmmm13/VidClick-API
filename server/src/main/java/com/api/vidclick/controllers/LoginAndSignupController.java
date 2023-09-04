package com.api.vidclick.controllers;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.ErrorMessageResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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
    private ResponseEntity<?> authenticateRequest(@RequestBody AuthenticationRequest request,
                                                  HttpServletRequest httpServletRequest, HttpServletResponse response){
        try{
            return authService.authenticate(httpServletRequest,request, response);
        } catch (Exception e){
            return ResponseEntity.status(400).body(new ErrorMessageResponse(e.getMessage()));
        }
    }
}

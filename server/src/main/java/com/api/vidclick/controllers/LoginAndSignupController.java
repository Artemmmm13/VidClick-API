package com.api.vidclick.controllers;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.ErrorMessageResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
                                                  HttpServletRequest httpServletRequest){
        try{
            return authService.authenticate(httpServletRequest,request);
        } catch (Exception e){
            return ResponseEntity.status(400).body(new ErrorMessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/upload-file")
    private ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (authService.isValidPhoto(file) && !file.isEmpty()){
            String originalFileName = file.getOriginalFilename();
            File storedFile = new File("/path/to/your/directory/" + originalFileName);
            try {
                file.transferTo(storedFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return ResponseEntity.status(200).body("File uploaded successfully");
        }
        else{
            throw new IOException("Uploaded file is not valid");
        }
    }
}

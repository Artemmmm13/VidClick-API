package com.api.vidclick.controllers;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.ErrorMessageResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
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
    private ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        if (authService.isValidPhoto(file)){
            String originalFileName = file.getOriginalFilename();
            assert originalFileName != null;
            if (originalFileName.isEmpty()){
                throw new IllegalArgumentException("Provide photo with non empty file name");
            }
            String projectRootPath = System.getProperty("user.dir");
            String uploadDirectoryPath = projectRootPath + "/server/src/main/resources/uploads";
            File directory = new File(uploadDirectoryPath);

            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    throw new RuntimeException("Failed to create directory: " + uploadDirectoryPath);
                }
            }


            File storedFile = new File(directory, originalFileName);

            try {
                file.transferTo(storedFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return ResponseEntity.status(200).body("File uploaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while uploading file");
        }

    }
}

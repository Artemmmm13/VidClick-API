package com.api.vidclick.controllers;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.AuthenticationResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.DTO.UpdateCreatorInfoRequest;
import com.api.vidclick.models.Creator;
import com.api.vidclick.repositories.CreatorRepository;
import java.io.IOException;

import com.api.vidclick.services.AuthenticationService;
import com.api.vidclick.services.UpdateCreatorProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/creator")
@RequiredArgsConstructor
public class CreatorController{

    private final AuthenticationService service;
    private final CreatorRepository repository;
    private final UpdateCreatorProfileService updateService;

    @GetMapping("/{requestedId}")
    public ResponseEntity<Optional<Creator>> getCreatorById(@PathVariable Long requestedId){ // todo (dont return pswrd)
        if (repository.existsById(requestedId)){
            Optional<Creator> creator = repository.findById(requestedId);
            return ResponseEntity.ok(creator);
        }
        return (ResponseEntity<Optional<Creator>>) ResponseEntity.notFound();
    }

    @PostMapping("/signup")
    private ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    private ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @PutMapping("/edit-profile/{requestedId}")
    private ResponseEntity<Void> updateCreatorAccountInfo(@PathVariable Long requestedId, @RequestBody UpdateCreatorInfoRequest updateCreatorInfoRequest){
        if (repository.existsById(requestedId)){
            updateService.updateAccountInfo(requestedId, updateCreatorInfoRequest);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}


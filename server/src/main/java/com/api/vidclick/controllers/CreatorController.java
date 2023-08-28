package com.api.vidclick.controllers;

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

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/creator")
@RequiredArgsConstructor
public class CreatorController{

    private final AuthenticationService authService;
    private final CreatorRepository repository;
    private final UpdateCreatorProfileService updateService;

    @GetMapping("/{requestedId}")
    public ResponseEntity<Creator> getCreatorById(@PathVariable Long requestedId){ // todo (dont return pswrd)
        if (repository.existsById(requestedId)){
            Creator creator = repository.findById(requestedId).orElseThrow(
                    ()-> new NoSuchElementException("The user with the given Id doesn't exist"));
            return ResponseEntity.ok(creator);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/refresh-token")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

    @PutMapping("/edit-profile/{requestedId}") // todo (exclude all sensitive data)
    private ResponseEntity<Void> updateCreatorAccountInfo(@PathVariable Long requestedId
            , @RequestBody UpdateCreatorInfoRequest updateCreatorInfoRequest){

        if (repository.existsById(requestedId)){
            updateService.updateAccountInfo(requestedId, updateCreatorInfoRequest);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}


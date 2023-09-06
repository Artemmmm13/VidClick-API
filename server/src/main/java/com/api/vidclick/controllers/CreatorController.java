package com.api.vidclick.controllers;

import com.api.vidclick.DTO.CreatorAsJsonResponse;
import com.api.vidclick.DTO.ErrorMessageResponse;
import com.api.vidclick.DTO.UpdateCreatorInfoRequest;
import com.api.vidclick.models.Creator;
import com.api.vidclick.repositories.CreatorRepository;
import java.io.IOException;

import com.api.vidclick.services.AuthenticationService;
import com.api.vidclick.services.LogoutService;
import com.api.vidclick.services.UpdateCreatorProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/creator")
@RequiredArgsConstructor
public class CreatorController{

    private final AuthenticationService authService;
    private final CreatorRepository repository;
    private final LogoutService logoutService;
    private final UpdateCreatorProfileService updateService;

    @GetMapping("/{requestedId}")
    public ResponseEntity<CreatorAsJsonResponse> getCreatorById(@PathVariable Long requestedId){
        if (repository.existsById(requestedId)){
            Creator requestedCreator = repository.findById(requestedId).orElseThrow(()->
                    new IllegalArgumentException("Creator with the given Id doesn't exist"));
            CreatorAsJsonResponse jsonResponse = new CreatorAsJsonResponse(requestedCreator.getId(),
                    requestedCreator.getName(), requestedCreator.getPassword(),
                    requestedCreator.getEmail(), requestedCreator.getCreatorProfileImage());
            return ResponseEntity.status(200).body(jsonResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/refresh-token")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

    @PutMapping("/edit-profile/{requestedId}")
    private ResponseEntity<Void> updateCreatorAccountInfo(@PathVariable Long requestedId
            , @RequestBody UpdateCreatorInfoRequest updateCreatorInfoRequest){

        if (repository.existsById(requestedId)){
            updateService.updateAccountInfo(requestedId, updateCreatorInfoRequest);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/logout")
    private ResponseEntity<?> logout(HttpServletRequest request){
        try{
            return logoutService.logout(request);
        } catch (Exception e){
            return ResponseEntity.status(400).body(new ErrorMessageResponse(e.getMessage()));
        }
    }

}


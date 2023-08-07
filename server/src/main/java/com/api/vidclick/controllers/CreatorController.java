package com.api.vidclick.controllers;

import com.api.vidclick.models.Creator;
import com.api.vidclick.repositories.CreatorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;


@RestController
@RequestMapping("/creator")
public class CreatorController{
    private final CreatorRepository repository;

    public CreatorController(CreatorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Creator> getCreatorById(@PathVariable Long requestedId, Principal principal){
        Creator creator = repository.findByIdAndName(requestedId, principal.getName());

        if (creator!=null){
            return ResponseEntity.ok(creator);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity makeNewCreatorAccount(@RequestBody Creator newCreatorRequest,
                                          UriComponentsBuilder ucb){
         Creator savedCreator = repository.save(newCreatorRequest);
        URI locationOfNewCreator = ucb
                .path("creator/{id}")
                .buildAndExpand(savedCreator.getId())
                .toUri();
        return ResponseEntity
                .created(locationOfNewCreator)
                .build();
    }

}

// Principal
// Spring Security

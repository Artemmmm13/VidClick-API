package com.api.vidclick.packages.controllers;

import com.api.vidclick.packages.models.Creator;
import com.api.vidclick.packages.repository.CreatorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creator")
public class CreatorController {

    private final CreatorRepository repository;

    public CreatorController(CreatorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Creator saveCreator(@RequestBody Creator creator){
        return repository.save(creator);
    }

    @GetMapping
    public List<Creator> getCreators(){
        return repository.findAll();
    }

}

package com.api.vidclick.packages.controllers;

import com.api.vidclick.packages.models.FundraisingOffer;
import com.api.vidclick.packages.repository.FundraisingOfferRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fundraising")
public class FundraisingController {
    private final FundraisingOfferRepository repository;

    public FundraisingController(FundraisingOfferRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public FundraisingOffer saveUser(@RequestBody FundraisingOffer offer){
        return repository.save(offer);
    }

    @GetMapping
    public List<FundraisingOffer> getFundraisingOffers(){
        return repository.findAll();
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<FundraisingOffer> findById(@PathVariable Integer requestedId) {
        Optional<FundraisingOffer> optionalFundraisingOffer = repository.findById(requestedId);

        if (optionalFundraisingOffer.isPresent()){
            FundraisingOffer entity = optionalFundraisingOffer.get();
            return ResponseEntity.ok(entity);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}

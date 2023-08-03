package com.api.vidclick.vidclick.controllers;

import com.api.vidclick.vidclick.models.FundraisingOffer;
import com.api.vidclick.vidclick.repository.FundraisingOfferRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

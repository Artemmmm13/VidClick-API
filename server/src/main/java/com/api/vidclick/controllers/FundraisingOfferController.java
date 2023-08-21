package com.api.vidclick.controllers;

import com.api.vidclick.DTO.CreateFundraisingOfferRequest;
import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import com.api.vidclick.services.CreateFundraisingOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fundraising-offers")
public class FundraisingOfferController {
    private final FundraisingOfferRepository repository;
    private final CreateFundraisingOfferService fundraisingOfferService;


    public FundraisingOfferController(FundraisingOfferRepository repository, CreateFundraisingOfferService fundraisingOfferService) {
        this.repository = repository;
        this.fundraisingOfferService = fundraisingOfferService;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Optional<FundraisingOffer>> getFundraisingOfferById(@PathVariable Long requestedId){
        if (repository.existsById(requestedId)){
            Optional<FundraisingOffer> offer = repository.findById(requestedId);
            return ResponseEntity.ok(offer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-offer")
    public ResponseEntity<FundraisingOffer> createFundraisingOffer(@RequestBody CreateFundraisingOfferRequest request){
        return fundraisingOfferService.createOffer(request);
    }
}

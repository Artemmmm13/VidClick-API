package com.api.vidclick.controllers;

import com.api.vidclick.DTO.CreateFundraisingOfferRequest;
import com.api.vidclick.DTO.UpdateFundraisingOfferRequest;
import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import com.api.vidclick.services.CreateFundraisingOfferService;
import com.api.vidclick.services.UpdateFundraisingOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/fundraising-offers")
public class FundraisingOfferController {
    private final FundraisingOfferRepository repository;
    private final CreateFundraisingOfferService fundraisingOfferService;
    private final UpdateFundraisingOfferService updateFundraisingOfferService;

    public FundraisingOfferController(FundraisingOfferRepository repository, CreateFundraisingOfferService fundraisingOfferService, UpdateFundraisingOfferService updateFundraisingOfferService) {
        this.repository = repository;
        this.fundraisingOfferService = fundraisingOfferService;
        this.updateFundraisingOfferService = updateFundraisingOfferService;
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
    public ResponseEntity<FundraisingOffer> createFundraisingOffer(@RequestBody CreateFundraisingOfferRequest createFundraisingOfferRequest){
        return fundraisingOfferService.createOffer(createFundraisingOfferRequest);
    }

    @PutMapping("/update-offer/{requestedId}")
    public ResponseEntity<Void> updateFundraisingOffer(@PathVariable Long requestedId,@RequestBody UpdateFundraisingOfferRequest updateFundraisingOfferRequest){
        if (repository.existsById(requestedId)){
            updateFundraisingOfferService.updateFundraisingOffer(requestedId, updateFundraisingOfferRequest);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

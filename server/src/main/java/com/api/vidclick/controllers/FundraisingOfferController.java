package com.api.vidclick.controllers;

import com.api.vidclick.DTO.CreateFundraisingOfferRequest;
import com.api.vidclick.DTO.UpdateFundraisingOfferRequest;
import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import com.api.vidclick.services.CreateFundraisingOfferService;
import com.api.vidclick.services.GetFundraisingOffersWithSortingService;
import com.api.vidclick.services.UpdateFundraisingOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fundraising-offers")
public class FundraisingOfferController {
    private final FundraisingOfferRepository repository;
    private final CreateFundraisingOfferService fundraisingOfferService;
    private final UpdateFundraisingOfferService updateFundraisingOfferService;
    private final GetFundraisingOffersWithSortingService sortingService;

    public FundraisingOfferController(FundraisingOfferRepository repository, CreateFundraisingOfferService fundraisingOfferService, UpdateFundraisingOfferService updateFundraisingOfferService, GetFundraisingOffersWithSortingService sortingService) {
        this.repository = repository;
        this.fundraisingOfferService = fundraisingOfferService;
        this.updateFundraisingOfferService = updateFundraisingOfferService;
        this.sortingService = sortingService;
    }

    @GetMapping("/offers-sorted-by/{field}")
    public ResponseEntity<List<FundraisingOffer>> getListOfFundraisingOffers(@PathVariable String field){
        return ResponseEntity.ok(sortingService.getSortedFundraisingOffers(field));
    }

    @GetMapping("/find-offer/{requestedId}")
    public ResponseEntity<Optional<FundraisingOffer>> getFundraisingOfferById(@PathVariable Long requestedId){
        if (repository.existsById(requestedId)){
            Optional<FundraisingOffer> offer = repository.findById(requestedId);
            return ResponseEntity.ok(offer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-offer")
    private ResponseEntity<FundraisingOffer> createFundraisingOffer(@RequestBody CreateFundraisingOfferRequest createFundraisingOfferRequest
            ,UriComponentsBuilder ucb){
        return fundraisingOfferService.createOffer(createFundraisingOfferRequest, ucb);
    }

    @PutMapping("/update-offer/{requestedId}")
    private ResponseEntity<Void> updateFundraisingOffer(@PathVariable Long requestedId,@RequestBody UpdateFundraisingOfferRequest updateFundraisingOfferRequest){
        if (repository.existsById(requestedId)){
            updateFundraisingOfferService.updateFundraisingOffer(requestedId, updateFundraisingOfferRequest);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-offer/{requestedId}")
    private ResponseEntity<Void> deleteFundraisingOffer(@PathVariable Long requestedId){
        if (repository.existsById(requestedId)){
            repository.deleteById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

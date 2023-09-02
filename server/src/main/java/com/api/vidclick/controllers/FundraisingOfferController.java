package com.api.vidclick.controllers;

import com.api.vidclick.DTO.CreateFundraisingOfferRequest;
import com.api.vidclick.DTO.FundraisingOfferAsJsonResponse;
import com.api.vidclick.DTO.UpdateFundraisingOfferRequest;
import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import com.api.vidclick.services.CreateFundraisingOfferService;
import com.api.vidclick.services.GetFundraisingOffersWithPaginationService;
import com.api.vidclick.services.GetFundraisingOffersWithSortingService;
import com.api.vidclick.services.UpdateFundraisingOfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fundraising-offers")
public class FundraisingOfferController {
    private final FundraisingOfferRepository repository;
    private final CreateFundraisingOfferService fundraisingOfferService;
    private final UpdateFundraisingOfferService updateFundraisingOfferService;
    private final GetFundraisingOffersWithSortingService sortingService;
    private final GetFundraisingOffersWithPaginationService fundraisingOffersWithPagination;

    public FundraisingOfferController(FundraisingOfferRepository repository, CreateFundraisingOfferService fundraisingOfferService
            , UpdateFundraisingOfferService updateFundraisingOfferService, GetFundraisingOffersWithSortingService sortingService
            , GetFundraisingOffersWithPaginationService fundraisingOffersWithPagination) {
        this.repository = repository;
        this.fundraisingOfferService = fundraisingOfferService;
        this.updateFundraisingOfferService = updateFundraisingOfferService;
        this.sortingService = sortingService;
        this.fundraisingOffersWithPagination = fundraisingOffersWithPagination;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<FundraisingOffer>> getProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FundraisingOffer> offersWithPagination = fundraisingOffersWithPagination.getFundraisingOfferWithPagination(pageable);
        return ResponseEntity.ok(offersWithPagination);
    }

    @GetMapping("/sorted-by/{field}")
    public ResponseEntity<List<FundraisingOffer>> getListOfFundraisingOffers(@PathVariable String field){
        return ResponseEntity.ok(sortingService.getSortedFundraisingOffers(field));
    }

    @GetMapping("/find/{requestedId}")
    public ResponseEntity<FundraisingOfferAsJsonResponse> getFundraisingOfferById(@PathVariable Long requestedId){
        if (repository.existsById(requestedId)){
            FundraisingOffer offer = repository.findById(requestedId).orElseThrow(
                    ()->new NoSuchElementException("The offer with the given id doesn't exist"));

            FundraisingOfferAsJsonResponse offerAsJsonResponse = new FundraisingOfferAsJsonResponse(offer.getCreatorId().getId(),
                    offer.getId(), offer.getTitle(), offer.getDescription(), List.of(offer.getPicturesOfFundraisingOffer().toString()),
                    offer.getAmount(), offer.getLinkToBankAccount(), offer.getOfferCreatedOn());
            return ResponseEntity.status(200).body(offerAsJsonResponse);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/create")
    private ResponseEntity<FundraisingOffer> createFundraisingOffer(@RequestBody CreateFundraisingOfferRequest createFundraisingOfferRequest
            ,UriComponentsBuilder ucb){
        return fundraisingOfferService.createOffer(createFundraisingOfferRequest, ucb);
    }

    @PutMapping("/update/{requestedId}")
    private ResponseEntity<Void> updateFundraisingOffer(@PathVariable Long requestedId
            ,@RequestBody UpdateFundraisingOfferRequest updateFundraisingOfferRequest){

        if (repository.existsById(requestedId)){
            updateFundraisingOfferService.updateFundraisingOffer(requestedId, updateFundraisingOfferRequest);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
// comment
    @DeleteMapping("/delete/{requestedId}")
    private ResponseEntity<Void> deleteFundraisingOffer(@PathVariable Long requestedId){
        if (repository.existsById(requestedId)){
            repository.deleteById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

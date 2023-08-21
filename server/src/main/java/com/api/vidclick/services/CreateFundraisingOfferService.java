package com.api.vidclick.services;

import com.api.vidclick.DTO.CreateFundraisingOfferRequest;
import com.api.vidclick.models.Creator;
import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.models.Role;
import com.api.vidclick.repositories.CreatorRepository;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CreateFundraisingOfferService {
    private final CreatorRepository creatorRepository;
    private final FundraisingOfferRepository fundraisingOfferRepository;

    public ResponseEntity<FundraisingOffer> createOffer(CreateFundraisingOfferRequest request) {
        Creator creator = creatorRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new NoSuchElementException("The account with such an id doesn't exist"));

        if (creator.getRole() != Role.CREATOR) {
            throw new NoSuchElementException("The user is not a registered or authenticated creator");
        }

        if (request.getTitle() == null || request.getTitle().isEmpty() ||
                request.getPicturesOfFundraisingOffer() == null || request.getPicturesOfFundraisingOffer().isEmpty() ||
                request.getAmountToGather() == null ||
                request.getLinkToBankAccount() == null || request.getLinkToBankAccount().isEmpty()) {
            throw new IllegalArgumentException("One or more request parameters are missing or empty");
        }

        FundraisingOffer newFundraisingOffer = FundraisingOffer.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .picturesOfFundraisingOffer(request.getPicturesOfFundraisingOffer())
                .amountToGather(request.getAmountToGather())
                .linkToBankAccount(request.getLinkToBankAccount())
                .offerCreatedOn(new Date())
                .creatorId(creatorRepository.findById(request.getCreatorId()).orElseThrow())
                .build();

        return ResponseEntity.ok(fundraisingOfferRepository.save(newFundraisingOffer));
    }
}

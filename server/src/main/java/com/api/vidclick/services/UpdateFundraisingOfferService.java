package com.api.vidclick.services;

import com.api.vidclick.DTO.UpdateFundraisingOfferRequest;
import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UpdateFundraisingOfferService {
    private final FundraisingOfferRepository repository;

    public UpdateFundraisingOfferService(FundraisingOfferRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void updateFundraisingOffer(Long id, UpdateFundraisingOfferRequest request){
        FundraisingOffer updatedOffer = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("The offer with the given id doesn't exist"));

        if (!request.getTitle().isEmpty()){
            updatedOffer.setTitle(request.getTitle());
        }

        if (!request.getDescription().isEmpty()){
            updatedOffer.setDescription(request.getDescription());
        }

        if (request.getPicturesOfFundraisingOffer() != null && !request.getPicturesOfFundraisingOffer().isEmpty()){
            updatedOffer.setPicturesOfFundraisingOffer(request.getPicturesOfFundraisingOffer()); // todo(what if user wants to delete or add photos)
        }

        if (request.getAmountToGather() != null){
            updatedOffer.setAmountToGather(request.getAmountToGather());
        }

        if (!request.getLinkToBankAccount().isEmpty()){
            updatedOffer.setLinkToBankAccount(request.getLinkToBankAccount());
        }
        repository.save(updatedOffer);
    }

}

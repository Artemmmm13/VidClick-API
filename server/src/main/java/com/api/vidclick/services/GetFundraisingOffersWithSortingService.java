package com.api.vidclick.services;

import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFundraisingOffersWithSortingService {
    private final FundraisingOfferRepository repository;

    public GetFundraisingOffersWithSortingService(FundraisingOfferRepository repository) {
        this.repository = repository;
    }

    public List<FundraisingOffer> getSortedFundraisingOffers(String field){
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
}

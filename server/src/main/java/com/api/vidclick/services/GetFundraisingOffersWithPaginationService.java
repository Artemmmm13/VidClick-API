package com.api.vidclick.services;

import com.api.vidclick.models.FundraisingOffer;
import com.api.vidclick.repositories.FundraisingOfferRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetFundraisingOffersWithPaginationService {
    private final FundraisingOfferRepository repository;

    public GetFundraisingOffersWithPaginationService(FundraisingOfferRepository repository) {
        this.repository = repository;
    }

    public Page<FundraisingOffer> getFundraisingOfferWithPagination(Pageable page){
        return repository.findAll(page);
    }
}

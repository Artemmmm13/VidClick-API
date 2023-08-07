package com.server.vidclick.repositories;

import com.server.vidclick.models.FundraisingOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FundraisingOfferRepository extends MongoRepository<FundraisingOffer, Long>, CrudRepository<FundraisingOffer, Long> {
    @Override
    Optional<FundraisingOffer> findById(Long id);
}
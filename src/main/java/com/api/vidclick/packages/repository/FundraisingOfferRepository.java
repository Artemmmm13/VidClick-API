package com.api.vidclick.packages.repository;

import com.api.vidclick.packages.models.FundraisingOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FundraisingOfferRepository extends CrudRepository<FundraisingOffer, Integer>, MongoRepository<FundraisingOffer, Integer> {
    Optional<FundraisingOffer> findById(Integer id);
}

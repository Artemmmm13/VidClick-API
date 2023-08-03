package com.api.vidclick.packages.repository;

import com.api.vidclick.packages.models.FundraisingOffer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundraisingOfferRepository extends MongoRepository<FundraisingOffer, Integer> {
}

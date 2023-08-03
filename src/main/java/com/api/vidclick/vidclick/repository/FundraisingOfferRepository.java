package com.api.vidclick.vidclick.repository;

import com.api.vidclick.vidclick.models.FundraisingOffer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundraisingOfferRepository extends MongoRepository<FundraisingOffer, Integer> {
}

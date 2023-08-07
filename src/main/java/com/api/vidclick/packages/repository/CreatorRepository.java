package com.api.vidclick.packages.repository;

import com.api.vidclick.packages.models.Creator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreatorRepository extends MongoRepository<Creator, Integer> {
}

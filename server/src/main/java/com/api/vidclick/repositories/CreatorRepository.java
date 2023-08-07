package com.api.vidclick.repositories;

import com.api.vidclick.models.Creator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface CreatorRepository extends MongoRepository<Creator, Long>, CrudRepository<Creator, Long> {
    Creator findByIdAndName(Long id, String name);
}

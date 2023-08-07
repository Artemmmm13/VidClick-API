package com.server.vidclick.repositories;

import com.server.vidclick.models.Creator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CreatorRepository extends MongoRepository<Creator, Long>, CrudRepository<Creator, Long> {
    Creator findByIdAndName(Long id, String name);
}

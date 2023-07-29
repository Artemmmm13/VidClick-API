package com.vidclick.API.repositories;

import com.vidclick.API.models.FundraisingPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<FundraisingPost, Long> {
    FundraisingPost findAllById(Long id);
}

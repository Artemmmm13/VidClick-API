package com.api.vidclick.vidclick.repository;

import com.api.vidclick.vidclick.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}

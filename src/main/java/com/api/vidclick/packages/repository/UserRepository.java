package com.api.vidclick.packages.repository;

import com.api.vidclick.packages.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}

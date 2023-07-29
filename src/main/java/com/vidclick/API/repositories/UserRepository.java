package com.vidclick.API.repositories;

import com.vidclick.API.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findAllByUserName(String userName);
}

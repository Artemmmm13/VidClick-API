package com.api.vidclick.services;


import com.api.vidclick.DTO.UpdateCreatorInfoRequest;
import com.api.vidclick.models.Creator;
import com.api.vidclick.repositories.CreatorRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class UpdateCreatorProfileService {
    private final CreatorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UpdateCreatorProfileService(CreatorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void updateAccountInfo(Long id, UpdateCreatorInfoRequest request){
        Creator creator = repository.findById(id).orElseThrow(()-> new NoSuchElementException("Creator with the given id " +
                "wasn't found"));
        if (request.getUserName() != null && !request.getUserName().isEmpty()){
            creator.setName(request.getUserName());
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()){
            creator.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getEmail()!=null && !request.getEmail().isEmpty()){
            creator.setEmail(creator.getEmail());
        }
        if (request.getCreatorProfileImage()!=null && !request.getCreatorProfileImage().isEmpty()) {
            creator.setCreatorProfileImage(request.getCreatorProfileImage());
        }

        repository.save(creator);

    }
}

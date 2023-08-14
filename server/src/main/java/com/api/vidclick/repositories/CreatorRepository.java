package com.api.vidclick.repositories;

import com.api.vidclick.models.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
    @Override
    @NonNull
    Optional<Creator> findById(@NonNull Long id);

    @NonNull
    Optional<Creator> findByName(@NonNull String name);

    @NonNull
    Optional<Creator> findByEmail(@NonNull String email);
}

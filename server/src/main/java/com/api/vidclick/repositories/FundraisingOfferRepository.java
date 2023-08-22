package com.api.vidclick.repositories;

import com.api.vidclick.models.FundraisingOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundraisingOfferRepository extends JpaRepository<FundraisingOffer, Long> {
    @NonNull
    boolean existsById(@NonNull Long id);

    @NonNull
    Optional<FundraisingOffer> findById(@NonNull Long id);
}

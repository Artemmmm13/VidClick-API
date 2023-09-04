package com.api.vidclick.repositories;

import com.api.vidclick.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query(value = """
select t from Token t inner join Creator c\s
on t.creator.id = c.id\s
where c.id = :id and (t.expired = false or t.revoked = false)\s
""")
    @NonNull
    List<Token> findAllValidTokenByCreator(@NonNull Long id);

    @Query(value = """
        SELECT t.id FROM Token t
        WHERE t.token = :token
    """)
    @NonNull
    Optional<Long> findByToken(@NonNull String token);
}

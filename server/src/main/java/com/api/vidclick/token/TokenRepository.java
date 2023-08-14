package com.api.vidclick.token;

import com.api.vidclick.models.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.LongBinaryOperator;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = """
select t from Token t inner join Creator c\s
on t.creator.id = c.id\s
where c.id = :id and (t.expired = false or t.revoked = false)\s
""")
    @NonNull
    List<Token> findAllValidTokenByCreator(@NonNull Long id);
    @NonNull
    Optional<Token> findByToken(@NonNull String token);
}

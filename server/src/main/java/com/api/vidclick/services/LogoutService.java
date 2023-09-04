package com.api.vidclick.services;

import com.api.vidclick.DTO.LogoutResponse;
import com.api.vidclick.models.Creator;
import com.api.vidclick.repositories.CreatorRepository;
import com.api.vidclick.repositories.TokenRepository;
import com.api.vidclick.token.Token;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class LogoutService {
    private final TokenRepository repository;
    private final CreatorRepository creatorRepository;


    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request){
        Cookie[] userCookies = request.getCookies();
        String userToken = userCookies[0].getValue();

        Long creatorId = repository.findByToken(userToken).orElseThrow(
                ()-> new NoSuchElementException("Such refresh token doesn't exist"));

        Creator creator = creatorRepository.findById(creatorId).orElseThrow(
                ()-> new NoSuchElementException("User with such refresh token doesn't exist"));

        List<Token> allCreatorTokens = repository.findAllValidTokenByCreator(creatorId);

        for (Token token: allCreatorTokens){
            token.setExpired(true);
            token.setRevoked(true);
            token.setToken("");
        }

        return ResponseEntity.status(200).body(new LogoutResponse(true));
    }
}

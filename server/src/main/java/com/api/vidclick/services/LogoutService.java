package com.api.vidclick.services;

import com.api.vidclick.DTO.LogoutResponse;
import com.api.vidclick.repositories.TokenRepository;
import com.api.vidclick.token.Token;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class LogoutService {
    private final TokenRepository repository;

    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request){
        Cookie[] userCookies = request.getCookies();
        String userToken = userCookies[0].getValue();

        Long creatorTokenId = repository.findByToken(userToken).orElseThrow(
                ()->new NoSuchElementException("The given token doesn't exist"));

        Token creatorToken = repository.findById(creatorTokenId).orElseThrow(
                ()->new NoSuchElementException("Given token id doesn't exist"));

        Long creatorId = creatorToken.getCreator().getId();

        List<Token> allCreatorTokens = repository.findAllValidTokenByCreator(creatorId);

        repository.deleteAll(allCreatorTokens);
        return ResponseEntity.status(200).body(new LogoutResponse(true));
    }
}

package com.api.vidclick.services;

import com.api.vidclick.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final TokenRepository repository;

    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth){

        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }

        jwt = authHeader.substring(7);
        var storedToken = repository.findByToken(jwt).orElseThrow(()->
                new NoSuchElementException("Logout operation is not possible"));
        if (storedToken!=null){
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            repository.save(storedToken);
            SecurityContextHolder.clearContext();
        }


    }
}

package com.api.vidclick.controllers;

import com.api.vidclick.models.Creator;
import com.api.vidclick.models.Role;
import com.api.vidclick.repositories.CreatorRepository;
import com.api.vidclick.securityConfig.JwtService;
import com.api.vidclick.token.Token;
import com.api.vidclick.token.TokenRepository;
import com.api.vidclick.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CreatorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var creator = Creator.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .creatorProfileImage(request.getCreatorProfileImage())
                .role(Role.CREATOR)
                .build();
        var savedCreator = repository.save(creator);
        var jwtToken = jwtService.generateToken(creator);
        var refreshToken = jwtService.generateToken(creator);
        saveCreatorToken(savedCreator, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        var creator = repository.findByEmail(request.getEmail())
                .orElseThrow(()-> new NoSuchElementException("User with such name doesn't exist"));
        var jwtToken = jwtService.generateToken(creator);
        var refreshToken = jwtService.generateRefreshToken(creator);
        revokeAllCreatorTokens(creator);
        saveCreatorToken(creator, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken)
                .build();
    }

    private void saveCreatorToken(Creator creator, String jwtToken){
        var token = Token.builder()
                .creator(creator)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllCreatorTokens(Creator creator){
        var validCreatorTokens = tokenRepository.findAllValidTokenByCreator(creator.getId());
        if (validCreatorTokens.isEmpty()) return;

        validCreatorTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validCreatorTokens);
    }

    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Beaver ")) return;

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail!= null){
            var creator = this.repository.findByEmail(userEmail).orElseThrow(
                    ()-> new NoSuchElementException("User with such an email doesn't exist"));

            if (jwtService.isTokenValid(refreshToken, creator)){
                var accessToken = jwtService.generateToken(creator);
                revokeAllCreatorTokens(creator);
                saveCreatorToken(creator, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }

        }
    }

}


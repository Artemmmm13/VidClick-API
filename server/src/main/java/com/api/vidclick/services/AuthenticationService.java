package com.api.vidclick.services;

import com.api.vidclick.DTO.AuthenticationRequest;
import com.api.vidclick.DTO.LoginResponse;
import com.api.vidclick.DTO.SignUpResponse;
import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.models.Creator;
import com.api.vidclick.models.Role;
import com.api.vidclick.repositories.CreatorRepository;
import com.api.vidclick.token.Token;
import com.api.vidclick.repositories.TokenRepository;
import com.api.vidclick.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.regex.Pattern;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.apache.commons.validator.routines.EmailValidator;
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

    private final Pattern patternForWhiteSpaces = Pattern.compile("\\s");
    private final Pattern patternForDigits = Pattern.compile("\\d");
    private final Pattern patternForSpecialChars = Pattern.compile("[^a-zA-Z0-9\\s]");

    public ResponseEntity<SignUpResponse> register(RegisterRequest request) {
        if (!isValidUserName(request.getName())){
            throw new IllegalArgumentException("User name should contain only alphabetic characters");
        }

        if (!isValidEmail(request.getEmail())){
            throw new IllegalArgumentException("Provided email is either non-valid or already used");
        }

        if (!isValidPassword(request.getPassword())){
            throw new IllegalArgumentException("Provided password does not satisfy our security requirements");
        }

        if (!isValidPhotoPath(request.getCreatorProfileImage())){
            throw new IllegalArgumentException("Provided file is either not a photo or it doesn't exist");
        }

        if (repository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("User with provided email already exists");
        }

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
        SignUpResponse response = new SignUpResponse(jwtToken, refreshToken, savedCreator);
        return ResponseEntity.status(201).body(response);
    }

    public ResponseEntity<LoginResponse> authenticate(AuthenticationRequest request) {
        if (!isValidEmail(request.getEmail())){
            throw new IllegalArgumentException("Provided email does not align with pattern for emails");
        }

        if (!isValidPassword(request.getPassword())){
            throw new IllegalArgumentException("The provided password doesn't meet our security requirements");
        }

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }

        var creator = repository.findByEmail(request.getEmail())
                .orElseThrow(()-> new NoSuchElementException("User with such email doesn't exist"));
        var jwtToken = jwtService.generateToken(creator);
        var refreshToken = jwtService.generateRefreshToken(creator);
        revokeAllCreatorTokens(creator);
        saveCreatorToken(creator, jwtToken);
        return ResponseEntity.status(200).body(new LoginResponse(jwtToken, refreshToken));
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

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) return;

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail!= null){
            var creator = this.repository.findByEmail(userEmail).orElseThrow(
                    ()-> new NoSuchElementException("User with such an email doesn't exist"));

            if (jwtService.isTokenValid(refreshToken, creator)){
                var accessToken = jwtService.generateToken(creator);
                revokeAllCreatorTokens(creator);
                saveCreatorToken(creator, accessToken);
                var authResponse = SignUpResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }

        }
    }

    private boolean isValidEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isValidPassword(String password){
        if (password.length() < 8){
            return false;
        }

        if (password.toLowerCase().equals(password)){
            return false;
        }

        if (password.toUpperCase().equals(password)){
            return false;
        }

        if (patternForWhiteSpaces.matcher(password).find()){
            return false;
        }

        if (!patternForSpecialChars.matcher(password).find()){
            return false;
        }

        return patternForDigits.matcher(password).find();
    }

    private boolean isValidUserName(String userName){
        if (patternForDigits.matcher(userName).find()){
            return false;
        }
        return !patternForSpecialChars.matcher(userName).find();
    }

    private boolean isValidPhotoPath(String photoPath){
        File photoFile = new File(photoPath);
        String userImageExtension = getPhotoExtension(photoPath);

        if (!photoFile.exists()){
            return false;
        }

        return isValidImageExtension(userImageExtension);
    }


    private String getPhotoExtension(String photoPath){
        int extensionIndex = photoPath.lastIndexOf(".");

        if (extensionIndex>0 && extensionIndex<photoPath.length()-1){
            return photoPath.substring(extensionIndex+1).toLowerCase();
        }
        return "";
    }


    private boolean isValidImageExtension(String imageExtension){
        String[] allowedExtensions = {"jpeg", "jpg", "svg", "png"};

        for (String str: allowedExtensions){
            if (str.equals(imageExtension)){
                return true;
            }
        }
        return false;
    }
}


package com.api.vidclick.services;

import com.api.vidclick.DTO.*;
import com.api.vidclick.models.Creator;
import com.api.vidclick.models.Role;
import com.api.vidclick.repositories.CreatorRepository;
import com.api.vidclick.token.Token;
import com.api.vidclick.repositories.TokenRepository;
import com.api.vidclick.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Base64;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
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
    private final TokenRepository tokenRepository;

    private final Pattern patternForWhiteSpaces = Pattern.compile("\\s");
    private final Pattern patternForDigits = Pattern.compile("\\d");
    private final Pattern patternForSpecialChars = Pattern.compile("[^a-zA-Z0-9\\s]");

    public ResponseEntity<SignUpResponse> register(RegisterRequest request, HttpServletResponse response) {
        if (!isValidUserName(request.getName())){
            throw new IllegalArgumentException("User name should contain only alphabetic characters");
        }

        if (!isValidEmail(request.getEmail())){
            throw new IllegalArgumentException("Provided email is either non-valid or already used");
        }

        if (!isValidPassword(request.getPassword())){
            throw new IllegalArgumentException("Provided password does not satisfy our security requirements");
        }

        /*if (!isValidPhotoPath(request.getCreatorProfileImage())){
            throw new IllegalArgumentException("Provided file is either not a photo or it doesn't exist");
        }*/

        byte[] profilePicture = new byte[0];
        if (request.getCreatorProfileImage()!=null){
            profilePicture = Base64.getDecoder().decode(request.getCreatorProfileImage());
        }

        if (repository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("User with provided email already exists");
        }

        var creator = Creator.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .creatorProfileImage(profilePicture)
                .role(Role.CREATOR)
                .build();
        var savedCreator = repository.save(creator);
        var jwtToken = jwtService.generateToken(creator);
        var refreshToken = jwtService.generateToken(creator);
        saveCreatorToken(savedCreator, jwtToken);
        Cookie cookie = new Cookie("user_session", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        CreatorAsJsonResponse creatorAsJsonResponse = new CreatorAsJsonResponse(savedCreator.getId(), savedCreator.getName(),
                savedCreator.getPassword(), savedCreator.getEmail(), request.getCreatorProfileImage());
        SignUpResponse jsonResponse = new SignUpResponse(jwtToken, refreshToken, creatorAsJsonResponse);
        return ResponseEntity.status(201).body(jsonResponse);
    }

    public ResponseEntity<LoginResponse> authenticate(HttpServletRequest httpServletRequest,AuthenticationRequest request) {
        if (!isValidEmail(request.getEmail())){
            throw new IllegalArgumentException("Provided email does not align with pattern for emails");
        }

        if (!isValidPassword(request.getPassword())){
            throw new IllegalArgumentException("The provided password doesn't meet our security requirements");
        }

        if (!repository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("User with the provided email doesn't exist");
        }

        var creator = repository.findByEmail(request.getEmail()).orElseThrow(()->
                new NoSuchElementException("User doesn't exist"));


        if (!passwordEncoder.matches(request.getPassword(), creator.getPassword())){
            throw new IllegalArgumentException("Incorrect password");
        }

        var jwtToken = jwtService.generateToken(creator);
        var refreshToken = jwtService.generateRefreshToken(creator);
        revokeAllCreatorTokens(creator);
        saveCreatorToken(creator, jwtToken);
        Cookie[] userCookies = httpServletRequest.getCookies();
        userCookies[0].setValue(refreshToken);
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

    private boolean isValidJPEGFile(String encodedFile){
        byte[] profilePicture = Base64.getDecoder().decode(encodedFile);
        return (profilePicture[0] == (byte) 0xFF && profilePicture[1] == (byte) 0xD8);
    }

    private boolean isValidPNGFile(String encodedFile){
        byte[] profilePicture = Base64.getDecoder().decode(encodedFile);
        return profilePicture[0] == (byte) 0x89 && profilePicture[1] == (byte) 0x50 && profilePicture[2] == (byte) 0x4E
                && profilePicture[3] == (byte) 0x47 && profilePicture[4] == (byte) 0x0D && profilePicture[5] == (byte) 0x0A
                && profilePicture[6] == (byte) 0x1A && profilePicture[7] == (byte) 0x0A;
    }

    private boolean isValidJPGFile(String encodedFile){
        byte[] profilePicture = Base64.getDecoder().decode(encodedFile);
        return profilePicture[0] == (byte) 0xFF && profilePicture[1] == (byte) 0xD8 && profilePicture[2] == (byte) 0xFF
                && (profilePicture[3] & 0xE0) == 0xE0;
    }

    private boolean isValidSVGFile(String encodedFile){
        return true;//todo
    }

}


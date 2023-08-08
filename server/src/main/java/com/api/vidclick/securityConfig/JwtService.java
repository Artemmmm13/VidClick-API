package com.api.vidclick.securityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "EFC5DC92568E1B336AFF26BAF41AF";

    public String extractUserName(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*168)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwt(jwtToken)
                .getBody();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails){
        final String userName = extractUserName(jwtToken);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken); // todo
    }

    private boolean isTokenExpired(String jwtToken){
        return extractExpirationDate(jwtToken).before(new Date());
    }

    private Date extractExpirationDate(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private Key getSigningKey() {
        byte[] bytesKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytesKey);
    }
}



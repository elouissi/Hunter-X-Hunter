package com.elouissi.hunters_league.config;

import com.elouissi.hunters_league.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private  final UserRepository userRepository;
    private static final String Secret_key = "3F8B9D5A1A7B49E2D7C0A0F4F3A5B8E2C3D4E5F6A7B8C9D0E1F2A3B4C5D6E7F8";
    public String extractUsername(String token){

        return extractClaim(token,Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extactExperation(token).before(new Date());

    }

    private Date extactExperation(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(Map<String,Object> extraClaims , UserDetails userDetails){

        extraClaims.put("role",getRole(userDetails.getUsername()));
        extraClaims.put("username",getUsername(userDetails.getUsername()));


        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String getRole(String username) {
        return userRepository.findByEmail(username).get().getRole().name();
    }
    private String getUsername(String username) {
        return userRepository.findByEmail(username).get().getName();
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extracatAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extracatAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Secret_key);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}

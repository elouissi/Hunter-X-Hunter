package com.elouissi.hunters_league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class KeycloakTokenService {
    @Autowired
    private JwtDecoder jwtDecoder;

    public Jwt decodeAndValidateToken(String token) {
        try {
            return jwtDecoder.decode(token);
        } catch (Exception e) {
            throw new RuntimeException("Token invalide", e);
        }
    }

    public String extractUsername(Jwt jwt) {
        return jwt.getClaimAsString("preferred_username");
    }

    public List<String> extractRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        return (List<String>) realmAccess.get("roles");
    }
}

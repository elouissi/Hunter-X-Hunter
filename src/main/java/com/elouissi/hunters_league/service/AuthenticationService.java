package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.config.JwtService;
import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.domain.enums.Role;
import com.elouissi.hunters_league.repository.UserRepository;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import com.elouissi.hunters_league.web.rest.controller.auth.AuthenticateRequest;
import com.elouissi.hunters_league.web.rest.controller.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(RegisterVM request) {
        var user = AppUser.builder()
                .firstName(request.getFirstName())
                .username(request.getUsername())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .cin(request.getCin())
                .joinDate(request.getJoinDate())
                .nationality(request.getNationality())
                .licenseExpirationDate(request.getLicenseExpirationDate())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.MEMBER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}

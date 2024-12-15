package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.service.AuthService;
import com.elouissi.hunters_league.service.DTO.AuthUserDTO;
import com.elouissi.hunters_league.web.rest.VM.LoginVM;
import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.LoginMapper;
import com.elouissi.hunters_league.web.rest.VM.mapper.RegisterMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegisterMapper registerMapper;
    private final AuthService authService;
    private final LoginMapper loginMapper;

    public AuthController(RegisterMapper registerMapper, AuthService authService, LoginMapper loginMapper) {
        this.registerMapper = registerMapper;
        this.loginMapper = loginMapper;
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginVM userLoginVM) {
        AppUser appUser = loginMapper.VmToEntity(userLoginVM);
        boolean isLoggedIn = authService.login(appUser);
        if (isLoggedIn) {
            return ResponseEntity.ok("Connexion réussie");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de la connexion");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<AuthUserDTO> register(@RequestBody @Valid RegisterVM registerVM){
        AppUser appUserEntity = registerMapper.VmToEntity(registerVM);
        AppUser appUser = authService.register(appUserEntity);
        AuthUserDTO userDTO = registerMapper.toDTO(appUser);
        return ResponseEntity.ok(userDTO);
    }

}

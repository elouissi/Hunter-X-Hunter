package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(AppUser appUserLogin) {
        AppUser appUser = userRepository.findByEmail(appUserLogin.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if (BCrypt.checkpw(appUserLogin.getPassword(), appUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


    public AppUser register(AppUser appUser) {
        String hashedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt());
        appUser.setPassword(hashedPassword);
        return userRepository.save(appUser);
    }

}

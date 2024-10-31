package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean login(User userLogin) {
        User user = authRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        if (BCrypt.checkpw(userLogin.getPassword(), user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


    public User register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return authRepository.save(user);
    }

}

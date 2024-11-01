package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(User userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail())
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
        return userRepository.save(user);
    }

}

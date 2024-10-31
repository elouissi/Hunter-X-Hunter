package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private  AuthRepository authRepository;

    public boolean login (User userLogin){
        User user = authRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(user.getPassword().equals(userLogin.getPassword())){
            return true;
        } else {
            throw new IllegalArgumentException("Password is incorrect");
        }

    }


}

package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.repository.UserRepository;
import com.elouissi.hunters_league.web.errors.NullVarException;
import com.elouissi.hunters_league.web.errors.UserAlreadyExistException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(User user) {
        if (user.getId() == null) throw new NullVarException("id is null");

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserAlreadyExistException("User id not exist"));

        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
                throw new UserAlreadyExistException("Username already exists");
            });
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                throw new UserAlreadyExistException("Email already exists");
            });
            existingUser.setEmail(user.getEmail());
        }
        if (user.getCin() != null && !user.getCin().equals(existingUser.getCin())) {
            userRepository.findByCin(user.getCin()).ifPresent(u -> {
                throw new UserAlreadyExistException("Cin already exists");
            });
            existingUser.setCin(user.getCin());
        }
        if (user.getPassword() != null) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            existingUser.setPassword(hashedPassword);
        }
        if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
        if (user.getNationality() != null) existingUser.setNationality(user.getNationality());
        if (user.getRole() != null) existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

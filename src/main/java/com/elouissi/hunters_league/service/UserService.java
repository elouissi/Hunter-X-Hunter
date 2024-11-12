package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.repository.HuntRepository;
import com.elouissi.hunters_league.repository.ParticipationRepository;
import com.elouissi.hunters_league.repository.UserRepository;
import com.elouissi.hunters_league.service.DTO.RankDTO;
import com.elouissi.hunters_league.web.errors.NullVarException;
import com.elouissi.hunters_league.web.errors.ObjectAlreadyExistException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private  ParticipationRepository participationRepository;
    @Autowired
    private HuntRepository huntRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User updateUser(User user) {
        if (user.getId() == null) throw new NullVarException("id is null");

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ObjectAlreadyExistException("User id not exist"));

        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Username already exists");
            });
            existingUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Email already exists");
            });
            existingUser.setEmail(user.getEmail());
        }
        if (user.getCin() != null && !user.getCin().equals(existingUser.getCin())) {
            userRepository.findByCin(user.getCin()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Cin already exists");
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
    public Optional<User> getUserByCin(String CIN) {
        return userRepository.findByCin(CIN);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Transactional
    public User remove(User user){
        huntRepository.deleteByParticipationUser(user);
        participationRepository.deleteByUser(user);
        userRepository.delete(user);
        return user;
    }

    public List<User> findByCriteria(String username, String email) {
        if (username != null && email != null) {
            return userRepository.findByUsernameContainingIgnoreCaseAndEmailContainingIgnoreCase(username, email);
        } else if (username != null) {
            return userRepository.findByUsernameContainingIgnoreCase(username);
        } else if (email != null) {
            return userRepository.findByEmailContainingIgnoreCase(email);
        } else {
            return userRepository.findAll();
        }
    }
    public List<RankDTO> getRankOfUserByScore(){
        return userRepository.getRankOfUserByScore();
    }

}

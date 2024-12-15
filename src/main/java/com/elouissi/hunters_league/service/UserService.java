package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.repository.HuntRepository;
import com.elouissi.hunters_league.repository.ParticipationRepository;
import com.elouissi.hunters_league.repository.UserRepository;
import com.elouissi.hunters_league.service.DTO.RankDTO;
import com.elouissi.hunters_league.web.errors.NullVarException;
import com.elouissi.hunters_league.web.errors.ObjectAlreadyExistException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<AppUser> getUsersPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public AppUser updateUser(AppUser appUser) {
        if (appUser.getId() == null) throw new NullVarException("id is null");

        AppUser existingAppUser = userRepository.findById(appUser.getId())
                .orElseThrow(() -> new ObjectAlreadyExistException("User id not exist"));

        if (appUser.getUsername() != null && !appUser.getUsername().equals(existingAppUser.getUsername())) {
            userRepository.findByUsername(appUser.getUsername()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Username already exists");
            });
            existingAppUser.setUsername(appUser.getUsername());
        }
        if (appUser.getEmail() != null && !appUser.getEmail().equals(existingAppUser.getEmail())) {
            userRepository.findByEmail(appUser.getEmail()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Email already exists");
            });
            existingAppUser.setEmail(appUser.getEmail());
        }
        if (appUser.getCin() != null && !appUser.getCin().equals(existingAppUser.getCin())) {
            userRepository.findByCin(appUser.getCin()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Cin already exists");
            });
            existingAppUser.setCin(appUser.getCin());
        }
        if (appUser.getPassword() != null) {
            String hashedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt());
            existingAppUser.setPassword(hashedPassword);
        }
        if (appUser.getFirstName() != null) existingAppUser.setFirstName(appUser.getFirstName());
        if (appUser.getLastName() != null) existingAppUser.setLastName(appUser.getLastName());
        if (appUser.getNationality() != null) existingAppUser.setNationality(appUser.getNationality());
        if (appUser.getRole() != null) existingAppUser.setRole(appUser.getRole());

        return userRepository.save(existingAppUser);
    }
    public Optional<AppUser> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }
    public Optional<AppUser> getUserByCin(String CIN) {
        return userRepository.findByCin(CIN);
    }

    public Optional<AppUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Transactional
    public AppUser remove(AppUser appUser){
        huntRepository.deleteByParticipationAppUser(appUser);
        participationRepository.deleteByAppUser(appUser);
        userRepository.delete(appUser);
        return appUser;
    }

    public List<AppUser> findByCriteria(String username, String email) {
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

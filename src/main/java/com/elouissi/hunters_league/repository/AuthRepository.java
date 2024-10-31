package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface AuthRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
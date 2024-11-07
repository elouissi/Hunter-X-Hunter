package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByCin(String cin);
    boolean existsByCin(String username);
    List<User> findByUsernameContainingIgnoreCase(String name);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByUsernameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email);
}
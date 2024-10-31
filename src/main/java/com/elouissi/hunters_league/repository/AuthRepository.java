package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Long> {
    public Optional<User> findByUsername(String username);
}

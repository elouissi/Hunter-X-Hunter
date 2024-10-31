package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> getUserByEmail(String email);
}

package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.service.DTO.RankDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID>{
    @Query("SELECT new com.elouissi.hunters_league.service.DTO.RankDTO(u.username, SUM(p.score)) " +
            "FROM User u INNER JOIN Participation p ON u.id = p.user.id " +
            "GROUP BY u.username " +
            "ORDER BY SUM(p.score) DESC limit 3")
    List<RankDTO> getRankOfUserByScore();
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByCin(String cin);
    boolean existsByCin(String username);
    List<User> findByUsernameContainingIgnoreCase(String name);

    List<User> findByEmailContainingIgnoreCase(String email);

    List<User> findByUsernameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email);
}
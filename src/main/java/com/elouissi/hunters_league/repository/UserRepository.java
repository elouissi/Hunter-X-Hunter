package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.AppUser;
import com.elouissi.hunters_league.service.DTO.RankDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<AppUser, UUID> {

    @Query("SELECT new com.elouissi.hunters_league.service.DTO.RankDTO(u.username, SUM(p.score)) " +
            "FROM AppUser u INNER JOIN Participation p ON u.id = p.appUser.id " +
            "GROUP BY u.username " +
            "ORDER BY SUM(p.score) DESC limit 3")
    List<RankDTO> getRankOfUserByScore();
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByCin(String cin);
    boolean existsByCin(String username);
    List<AppUser> findByUsernameContainingIgnoreCase(String name);

    List<AppUser> findByEmailContainingIgnoreCase(String email);
    boolean existsByEmail(String email);

    List<AppUser> findByUsernameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email);
}
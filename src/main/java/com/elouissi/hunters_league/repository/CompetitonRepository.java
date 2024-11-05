package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitonRepository extends JpaRepository<Competition,Long> {
    Optional<Competition> findByCode (String name);
    Optional<Competition> findById(UUID id);
    boolean existsByCode(String code);


}

// Package: com.elouissi.hunters_league.repository
package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.service.DTO.CompetitionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    @Query("SELECT new com.elouissi.hunters_league.service.DTO.CompetitionDTO(c.id,c.location,c.date,c.openRegistration,c.speciesType,c.minParticipants,c.maxParticipants,  COUNT(p)) " +
            "FROM Competition c LEFT JOIN c.participations p " +
            "WHERE c.id = :competitionId " +
            "GROUP BY c.location, c.date")
    CompetitionDTO getCompetitionWithParticipationCount(@Param("competitionId") UUID competitionId);

    Optional<Competition> findByCode(String name);

    Optional<Competition> findById(UUID id);

    boolean existsByCode(String code);
}

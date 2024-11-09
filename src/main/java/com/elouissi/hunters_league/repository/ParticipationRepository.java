package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation , Long> {
    public void deleteByUser(User user);
    public void deleteByCompetition(Competition competition);
    public Optional<Participation> getParticipationById(UUID uuid);

}

package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.repository.CompetitonRepository;
import com.elouissi.hunters_league.repository.ParticipationRepository;
import com.elouissi.hunters_league.web.errors.NullVarException;
import com.elouissi.hunters_league.web.errors.ObjectAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {
    private CompetitonRepository competitionRepository;
    private ParticipationRepository participationRepository;

    public CompetitionService(CompetitonRepository competitionRepository,ParticipationRepository participationRepository) {
        this.competitionRepository = competitionRepository;
        this.participationRepository = participationRepository;
    }

    public List<Competition> getALl(){
        return competitionRepository.findAll();
    }

    public Competition save(Competition competition){
        competition.setOpenRegistration(true);
        return (Competition) competitionRepository.save(competition);
    }
    public Optional<Competition> getCompetitionBycode(String code) {
        return competitionRepository.findByCode(code);
    }
    public Competition remove(Competition competition){
        participationRepository.deleteByCompetition(competition);
        competitionRepository.delete(competition);
        return competition;
    }
    public Competition updateCompetition(Competition competition) {
        if (competition.getId() == null) throw new NullVarException("id is null");

        Competition existingCompetition = competitionRepository.findById(competition.getId())
                .orElseThrow(() -> new ObjectAlreadyExistException("Competition id not exist"));

        if (competition.getCode() != null && !competition.getCode().equals(competition.getCode())) {
            competitionRepository.findByCode(competition.getCode()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Competition code already exists");
            });
            competition.setCode(competition.getCode());
        }
        if (competition.getDate() != null) competition.setDate(competition.getDate());
        if (competition.getLocation() != null) competition.setLocation(competition.getLocation());
        if (competition.getParticipations() != null) competition.setParticipations(competition.getParticipations());
        if (competition.getOpenRegistration() != null) competition.setOpenRegistration(competition.getOpenRegistration());
        if (competition.getMaxParticipants() != null) competition.setMaxParticipants(competition.getMaxParticipants());
        if (competition.getMinParticipants() != null) competition.setMinParticipants(competition.getMinParticipants());


        return competitionRepository.save(competition);
    }

}

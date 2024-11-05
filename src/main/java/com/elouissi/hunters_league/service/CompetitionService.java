package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.repository.CompetitonRepository;
import com.elouissi.hunters_league.repository.SpecieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {
    private CompetitonRepository competitionRepository;

    public CompetitionService(CompetitonRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<Competition> getALl(){
        return competitionRepository.findAll();
    }
    public Competition save(Competition competition){
        return (Competition) competitionRepository.save(competition);
    }
    public Optional<Competition> getCompetitionBycode(String code) {
        return competitionRepository.findByCode(code);
    }
    public Competition remove(Competition competition){
        competitionRepository.delete(competition);
        return competition;
    }
}

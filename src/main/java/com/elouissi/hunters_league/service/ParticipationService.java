package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipationService {
    private ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public List<Participation> getALl(){
        return participationRepository.findAll();
    }
    public Participation save(Participation participation){
        return (Participation) participationRepository.save(participation);
    }
}

package com.elouissi.hunters_league.scheduler;

import com.elouissi.hunters_league.domain.Hunt;
import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.service.CompetitionService;
import com.elouissi.hunters_league.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScoreOfParticipation {
    @Autowired
    private ParticipationService participationService;
    @Scheduled(cron = "0 * * * * *")
    public void CalculateScoreOfAllParticipation(){
        List<Participation> participationList = participationService.getAll();
        for (Participation participation:participationList){
            for (Hunt hunt: participation.getHunts()){
                Double score = hunt.getSpecies().getPoints() + (hunt.getWeight() * hunt.getSpecies().getCategory().getValue()) + hunt.getSpecies().getDifficulty().getValue();
                participation.setScore(participation.getScore()+score);
                participationService.update(participation);
            }
        }

    }


}

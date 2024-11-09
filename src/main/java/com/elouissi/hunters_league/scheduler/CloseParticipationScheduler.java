package com.elouissi.hunters_league.scheduler;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class CloseParticipationScheduler {

    @Autowired
    private CompetitionService competitionService;

    public boolean isParticipationOlderThan2Days(Competition competition, int days) {
        LocalDateTime started_at = competition.getDate();
        LocalDateTime now = LocalDateTime.now();
        long daysDifference = ChronoUnit.DAYS.between(now, started_at);
        return daysDifference <= days && daysDifference >= 0;
    }

    @Scheduled(fixedRate = 60000)
    private void checkAndUpdateCompetition() {
        try {
            List<Competition> competitions = competitionService.getALl();
            for (Competition competition : competitions) {
                if (isParticipationOlderThan2Days(competition, 2)) {
                    competition.setOpenRegistration(false);
                    competitionService.updateCompetition(competition);
                    System.out.println("Mise à jour de la compétition réussie.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des compétitions : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

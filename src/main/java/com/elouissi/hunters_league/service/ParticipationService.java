package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.repository.ParticipationRepository;
import com.elouissi.hunters_league.service.DTO.ParticipationDTO;
import com.elouissi.hunters_league.web.rest.VM.ParticipationVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.ParticipationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserService userService;
    private final CompetitionService competitionService;
    private final ParticipationMapper participationMapper;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository, UserService userService, CompetitionService competitionService, ParticipationMapper participationMapper) {
        this.participationRepository = participationRepository;
        this.userService = userService;
        this.competitionService = competitionService;
        this.participationMapper = participationMapper;
    }

    public ParticipationDTO saveParticipation(ParticipationVM participationVM) {
        String cin = participationVM.getCin();
        String code = participationVM.getCompetitionCode();

        User user = userService.getUserByCin(cin)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
        Competition competition = competitionService.getCompetitionBycode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compétition non trouvée"));
        if (competition.getOpenRegistration() && competition.getParticipations().size() <= competition.getMaxParticipants()) {

            Participation participation = participationMapper.VmToEntity(participationVM);
            participation.setUser(user);
            participation.setCompetition(competition);
            Participation savedParticipation = participationRepository.save(participation);
            competition.getParticipations().add(savedParticipation);
            competitionService.updateCompetition(competition);
            ParticipationDTO participationDTO = participationMapper.toDTO(savedParticipation);
            return participationDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'inscription est fermée ou la limite de participants est atteinte");
        }

    }
    public Participation update(Participation participation) {
        Participation existingParticipation = participationRepository.getParticipationById(participation.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participation non trouvée"));

        existingParticipation.setScore(participation.getScore());
        existingParticipation.setUser(participation.getUser());
        existingParticipation.setCompetition(participation.getCompetition());

        return participationRepository.save(existingParticipation);
    }

    public Optional<Participation> getById(UUID uuid){
        return participationRepository.getParticipationById(uuid);
    }
}

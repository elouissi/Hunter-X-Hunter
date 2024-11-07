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

        Participation participation = participationMapper.VmToEntity(participationVM);
        participation.setUser(user);
        participation.setCompetition(competition);
        Participation participation1 = participationRepository.save(participation);
        ParticipationDTO participationDTO = participationMapper.toDTO(participation1);
        return participationDTO;

    }
    public Optional<Participation> getById(UUID uuid){
        return participationRepository.getParticipationById(uuid);
    }
}
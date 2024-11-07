package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.domain.User;
import com.elouissi.hunters_league.service.CompetitionService;
import com.elouissi.hunters_league.service.ParticipationService;
import com.elouissi.hunters_league.service.UserService;
import com.elouissi.hunters_league.web.rest.VM.ParticipationVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.ParticipationMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/participation")
public class ParticipationController {
    @Autowired
    private ParticipationMapper participationMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ParticipationService participationService;
    @PostMapping("/participer")
    public ResponseEntity<String> save(@RequestBody @Valid ParticipationVM participationVM) {
        String cin = participationVM.getCin();
        String code = participationVM.getCompetitionCode();

        User user = userService.getUserByCin(cin)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
        Competition competition = competitionService.getCompetitionBycode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compétition non trouvée"));

        Participation participation = participationMapper.VmToEntity(participationVM);
        participation.setUser(user);
        participation.setCompetition(competition);
        participationService.save(participation);

        return ResponseEntity.ok("Participation enregistrée avec succès");
    }

}

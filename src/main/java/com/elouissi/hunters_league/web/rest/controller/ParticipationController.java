package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.service.ParticipationService;
import com.elouissi.hunters_league.web.rest.VM.ParticipationVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participation")
public class ParticipationController {

    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping("/participer")
    public ResponseEntity<String> save(@RequestBody @Valid ParticipationVM participationVM) {
        participationService.saveParticipation(participationVM);
        return ResponseEntity.ok("Participation enregistrée avec succès");
    }
}

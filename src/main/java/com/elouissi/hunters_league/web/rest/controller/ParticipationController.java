package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.service.DTO.ParticipationDTO;
import com.elouissi.hunters_league.service.ParticipationService;
import com.elouissi.hunters_league.web.rest.VM.ParticipationVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.ParticipationMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/participation")
public class ParticipationController {

    private final ParticipationService participationService;
    @Autowired
    private ParticipationMapper participationMapper;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

//    @GetMapping("/calculate-scores")
//    public String calculateScores() {
//        participationService.calculateScoreOfAllParticipation();
//        return "Scores updated successfully!";
//    }
    @PostMapping("/participer")
    public ResponseEntity<?> save(@RequestBody @Valid ParticipationVM participationVM) {
        try {
            ParticipationDTO participation=  participationService.saveParticipation(participationVM);

            return ResponseEntity.ok(participation);
        }  catch (ResponseStatusException e) {

        return ResponseEntity.status(e.getStatusCode()).body("L'inscription est fermée ou la limite de participants est atteinte");
        }

    }
    @PostMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ParticipationVM participationVM, @PathVariable UUID id) {
        Optional<Participation> participationOptional = participationService.getById(id);

        if (participationOptional.isPresent()) {
            Participation existingParticipation = participationOptional.get();
            Participation participationToUpdate = participationMapper.VmToEntity(participationVM);
            participationToUpdate.setId(existingParticipation.getId());

            Participation updatedParticipation = participationService.update(participationToUpdate);
            return ResponseEntity.ok("Participation mise à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La participation n'existe pas.");
        }
    }
    @GetMapping("/Consultation/{cin}")
    public ResponseEntity<List<Participation>> getMyCompetition(@PathVariable String cin) {
        List<Participation> mesHistorique = participationService.getMyHistorique(cin);
        return ResponseEntity.ok(mesHistorique);
    }


}

package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Competition;

import com.elouissi.hunters_league.service.CompetitionService;
import com.elouissi.hunters_league.web.rest.VM.CompetitionVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.CompetitionMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {
    @Autowired
    private  CompetitionMapper competitionMapper;
    @Autowired
    private CompetitionService competitionService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid CompetitionVM competitionVM){
        Competition competition = competitionMapper.VmToEntity(competitionVM);
        competitionService.save(competition);
        return ResponseEntity.ok("le specie a bien ete creer");

    }
    @GetMapping("remove/{code}")
    public ResponseEntity<String> remove(@PathVariable String code){
        Optional<Competition> competitionOptional = competitionService.getCompetitionBycode(code);
        if (competitionOptional.isPresent()){
            Competition competition = competitionOptional.get();
            competitionService.remove(competition);
            return ResponseEntity.ok("la competition est supprimé");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La competition n'existe pas.");
        }
    }
    @PostMapping("update/{code}")
    public ResponseEntity<?> update(@RequestBody @Valid CompetitionVM competitionVM , @PathVariable String code){
        Optional<Competition> competitionOptional = competitionService.getCompetitionBycode(code);

        if (competitionOptional.isPresent()) {
            Competition existingCompetition = competitionOptional.get();
            Competition specieToUpdate = competitionMapper.VmToEntity(competitionVM);
//            specieToUpdate.setId(Competition.getId());
//
//            Competition updatedCompetition = competitionService.(specieToUpdate);
            return ResponseEntity.ok("Specie mis à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le specie n'existe pas.");
        }


    }
}

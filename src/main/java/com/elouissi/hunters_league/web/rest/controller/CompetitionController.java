package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Competition;

import com.elouissi.hunters_league.service.CompetitionService;
import com.elouissi.hunters_league.service.DTO.CompetitionDTO;
import com.elouissi.hunters_league.web.rest.VM.CompetitionVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.CompetitionMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {
    @Autowired
    private  CompetitionMapper competitionMapper;
    @Autowired
    private CompetitionService competitionService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid CompetitionVM competitionVM){
        Competition competition = competitionMapper.VmToEntity(competitionVM);
        competitionService.save(competition);
        return ResponseEntity.ok("le specie a bien ete creer");

    }

    @GetMapping("/result/{uuid}")
    public ResponseEntity<CompetitionDTO> getCompetitonWithParticipation(@PathVariable UUID uuid){
        CompetitionDTO competitionDTO =competitionService.getCompetitionWithParticipation(uuid);
        return ResponseEntity.ok(competitionDTO);
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
    @GetMapping("getAll")
    public ResponseEntity<List<Competition>> getAll(){
        List<Competition> competitionList = competitionService.getALl();
        return ResponseEntity.ok(competitionList);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getByCode/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code ){
        Optional<Competition> competitionFiltrer = competitionService.getCompetitionBycode(code);
        if (competitionFiltrer.isPresent()){
            Competition competition = competitionFiltrer.get();
            return ResponseEntity.ok(competition);
        }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("la competition ne trouve pas");
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("update/{code}")
    public ResponseEntity<?> update(@RequestBody @Valid CompetitionVM competitionVM , @PathVariable String code){
        Optional<Competition> competitionOptional = competitionService.getCompetitionBycode(code);

        if (competitionOptional.isPresent()) {
            Competition existingCompetition = competitionOptional.get();
            Competition competitionToUpdate = competitionMapper.VmToEntity(competitionVM);
            competitionToUpdate.setId(existingCompetition.getId());

            Competition updatedCompetition = competitionService.updateCompetition(competitionToUpdate);
            return ResponseEntity.ok("Competition mis à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La competition n'existe pas.");
        }


    }
}

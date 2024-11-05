package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Competition;
import com.elouissi.hunters_league.service.CompetitionService;
import com.elouissi.hunters_league.web.rest.VM.CompetitionVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.CompetitionMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.service.SpecieService;
import com.elouissi.hunters_league.web.rest.VM.SpecieVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.SpecieMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/specie")
public class SpecieController {
    @Autowired
    private SpecieService specieService;
    @Autowired
    private SpecieMapper specieMapper;

    @PostMapping("/save")
    public ResponseEntity<?> save( @RequestBody @Valid SpecieVM specieVM ){
        Species specie =  specieMapper.VmToEntity(specieVM);
        specieService.save(specie);
        return ResponseEntity.ok("le specie a bien ete creer");

    }
}

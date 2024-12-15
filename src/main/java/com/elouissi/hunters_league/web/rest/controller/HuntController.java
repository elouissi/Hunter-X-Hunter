package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Hunt;
import com.elouissi.hunters_league.service.DTO.ParticipationDTO;
import com.elouissi.hunters_league.service.HuntService;
import com.elouissi.hunters_league.web.rest.VM.HuntVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hunt")
public class HuntController {
    @Autowired
    private HuntService huntService;
    @PreAuthorize("hasRole('ADMIN') || hasRole('JURY')")
    @PostMapping("/save")
    public ResponseEntity<Hunt> save(@RequestBody @Valid HuntVM huntVM){
        Hunt hunt=  huntService.save(huntVM);

        return ResponseEntity.ok(hunt);
    }
}

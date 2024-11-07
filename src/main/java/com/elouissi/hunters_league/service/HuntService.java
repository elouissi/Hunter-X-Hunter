package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Hunt;
import com.elouissi.hunters_league.domain.Participation;
import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.repository.HuntRepository;
import com.elouissi.hunters_league.web.rest.VM.HuntVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.HuntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class HuntService {
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private SpecieService specieService;
    private final HuntRepository huntRepository;
    private final HuntMapper huntMapper;

    public HuntService(HuntRepository huntRepository, HuntMapper huntMapper){
        this.huntRepository = huntRepository;
        this.huntMapper = huntMapper;
    }
    public Hunt save(HuntVM huntVM){
        UUID code = huntVM.getParticipationID();
        String nameS = huntVM.getNameSpecie();

        Participation participation = participationService.getById(code).orElseThrow( ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"la participation non trouvé"));
        Species species = specieService.getSpeciesByname(nameS).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"le specie est non trouver"));
        Hunt hunt = huntMapper.VmToEntity(huntVM);
        hunt.setSpecies(species);
        hunt.setParticipation(participation);
        return huntRepository.save(hunt);


    }
}

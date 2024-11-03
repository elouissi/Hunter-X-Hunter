package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.repository.SpecieRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecieService {
    private SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }
    public Species save(Species species){
       return (Species) specieRepository.save(species);
    }
}

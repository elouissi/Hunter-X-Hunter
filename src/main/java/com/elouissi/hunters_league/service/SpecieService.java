package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.repository.SpecieRepository;
import com.elouissi.hunters_league.web.errors.NullVarException;
import com.elouissi.hunters_league.web.errors.ObjectAlreadyExistException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecieService {
    private SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public List<Species> getALl(){
        return specieRepository.findAll();
    }
    public Species save(Species species){
       return (Species) specieRepository.save(species);
    }
    public Optional<Species> getSpeciesByname(String name) {
        return specieRepository.findByName(name);
    }
    public Species remove(Species species){
         specieRepository.delete(species);
        return species;
    }
    public Species updateSpecies(Species specie) {
        if (specie.getId() == null) throw new NullVarException("id is null");

        Species existingSpecies = specieRepository.findById(specie.getId())
                .orElseThrow(() -> new ObjectAlreadyExistException("Species id not exist"));

        if (specie.getName() != null && !specie.getName().equals(existingSpecies.getName())) {
            specieRepository.findByName(specie.getName()).ifPresent(u -> {
                throw new ObjectAlreadyExistException("Speciesname already exists");
            });
            existingSpecies.setName(specie.getName());
        }

        if (specie.getCategory() != null) existingSpecies.setCategory(specie.getCategory());
        if (specie.getPoints() != null) existingSpecies.setPoints(specie.getPoints());
        if (specie.getDifficulty() != null) existingSpecies.setDifficulty(specie.getDifficulty());
        if (specie.getMinimumWeight() != null) existingSpecies.setMinimumWeight(specie.getMinimumWeight());

        return specieRepository.save(existingSpecies);
    }
}

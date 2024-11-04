package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.service.SpecieService;
import com.elouissi.hunters_league.web.rest.VM.SpecieVM;
import com.elouissi.hunters_league.web.rest.VM.mapper.SpecieMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/specie")
public class SpecieController {
    @Autowired
    private SpecieService specieService;
    @Autowired
    private SpecieMapper specieMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<Species>> getALl(){
        List<Species> species = specieService.getALl();
        return ResponseEntity.ok(species);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save( @RequestBody @Valid SpecieVM specieVM ){
        Species specie =  specieMapper.VmToEntity(specieVM);
        specieService.save(specie);
        return ResponseEntity.ok("le specie a bien ete creer");

    }
    @GetMapping("/delete/{name}")
    public ResponseEntity<?> remove(@PathVariable String name){
        Optional<Species> SpecieOptional = specieService.getSpeciesByname(name);
        if (SpecieOptional.isPresent()){
            Species existingSpecies = SpecieOptional.get();
            Species deletedSpecies = specieService.remove(existingSpecies);
            return ResponseEntity.ok("le specier a rejeté avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le specier n'existe pas.");
        }

    }
    @PostMapping("/update/{name}")
    public ResponseEntity<?> updateSpecie(@Valid @RequestBody SpecieVM specieVM, @PathVariable String name) {
        Optional<Species> specieOptional = specieService.getSpeciesByname(name);

        if (specieOptional.isPresent()) {
            Species existingSpecies = specieOptional.get();
            Species specieToUpdate = specieMapper.VmToEntity(specieVM);
            specieToUpdate.setId(existingSpecies.getId());

            Species updatedSpecies = specieService.updateSpecies(specieToUpdate);
            return ResponseEntity.ok("Specie mis à jour avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le specie n'existe pas.");
        }
    }
}

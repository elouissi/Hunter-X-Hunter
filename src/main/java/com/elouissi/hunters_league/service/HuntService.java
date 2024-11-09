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
    public Hunt save(HuntVM huntVM) {
        UUID code = huntVM.getParticipationID();
        String nameS = huntVM.getNameSpecie();
        Participation participation = participationService.getById(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La participation n'a pas été trouvée"));
        Species species = specieService.getSpeciesByname(nameS)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'espèce n'a pas été trouvée"));

        if (species.getMinimumWeight() > huntVM.getWeight()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le poids de la chasse est inférieur au poids minimal de l'espèce.");
        }

        Hunt hunt = huntMapper.VmToEntity(huntVM);

        hunt.setSpecies(species);
        hunt.setParticipation(participation);

        Double score = species.getPoints() + (hunt.getWeight() * species.getCategory().getValue()) + species.getDifficulty().getValue();

        participation.setScore(participation.getScore() +score);
        participationService.update(participation);
        return huntRepository.save(hunt);
    }

}

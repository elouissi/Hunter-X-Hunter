package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.domain.enums.SpeciesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecieRepository extends JpaRepository<Species,Long> {
    Optional<Species> findByName (String name);
    Optional<Species> findById(UUID id);
    List<Species> getSpeciesByCategory(SpeciesType category);


}

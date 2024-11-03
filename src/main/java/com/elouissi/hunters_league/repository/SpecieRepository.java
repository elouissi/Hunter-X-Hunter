package com.elouissi.hunters_league.repository;

import com.elouissi.hunters_league.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Species,Long> {
}

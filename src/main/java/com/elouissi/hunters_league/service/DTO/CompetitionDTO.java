
package com.elouissi.hunters_league.service.DTO;

import com.elouissi.hunters_league.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.UUID;

public class CompetitionDTO {
    private String location;
    private UUID id;

    private LocalDateTime date;
    private Long participationCount;

    private Integer minParticipants;

    private Integer maxParticipants;
    private SpeciesType speciesType;

    public UUID getId() {
        return id;

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public SpeciesType getSpeciesType() {
        return speciesType;
    }

    public void setSpeciesType(SpeciesType speciesType) {
        this.speciesType = speciesType;
    }

    public Boolean getOpenRegistration() {
        return openRegistration;
    }

    public void setOpenRegistration(Boolean openRegistration) {
        this.openRegistration = openRegistration;
    }

    private Boolean openRegistration;


    public CompetitionDTO(UUID id,String location,  LocalDateTime date, Boolean openRegistration,SpeciesType speciesType,  Integer minParticipants, Integer maxParticipants,Long participationCount ) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.participationCount = participationCount;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.speciesType = speciesType;
        this.openRegistration = openRegistration;
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(Long participationCount) {
        this.participationCount = participationCount;
    }

    public Integer getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(Integer minParticipants) {
        this.minParticipants = minParticipants;
    }
}

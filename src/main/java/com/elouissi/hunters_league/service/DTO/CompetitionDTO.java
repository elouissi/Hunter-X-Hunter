
package com.elouissi.hunters_league.service.DTO;

import java.time.LocalDateTime;

public class CompetitionDTO {
    private String location;
    private LocalDateTime date;
    private Long participationCount;

    public CompetitionDTO(String location, LocalDateTime date, Long participationCount) {
        this.location = location;
        this.date = date;
        this.participationCount = participationCount;
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
}

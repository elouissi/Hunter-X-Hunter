package com.elouissi.hunters_league.service.DTO;

import java.time.LocalDateTime;


public class RankDTO {
    private String username;
    private Double score;

    public RankDTO(String username, Double score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

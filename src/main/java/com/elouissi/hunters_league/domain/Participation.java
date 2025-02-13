package com.elouissi.hunters_league.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participation{

    @Id @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Competition competition;

    @OneToMany(mappedBy = "participation")
    private List<Hunt> hunts;

    private Double score;

}

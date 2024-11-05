package com.elouissi.hunters_league.web.rest.VM;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationVM {

    @NotBlank(message = "Le CIN de l'utilisateur est requis")
    private String cin;


    @NotBlank(message = "Le code de la compétition est requis")
    private String competitionCode;


    @NotNull(message = "Le score est requis")
    @PositiveOrZero(message = "Le score doit être positif ou égal à zéro")
    private Double score;
}

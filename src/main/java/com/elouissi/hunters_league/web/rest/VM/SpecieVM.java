package com.elouissi.hunters_league.web.rest.VM;

import com.elouissi.hunters_league.domain.enums.Difficulty;
import com.elouissi.hunters_league.domain.enums.SpeciesType;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecieVM {

    @NotBlank(message = "Le nom est requis")
    private String name;

    @NotNull(message = "Le poids minimum est requis")
    @Positive(message = "Le poids minimum doit être un nombre positif")
    private Double minimumWeight;

    @NotNull(message = "La catégorie est requise")
    private SpeciesType category;

    @NotNull(message = "La difficulté est requise")
    private Difficulty difficulty;

    @NotNull(message = "Les points sont requis")
    private Integer points;

}


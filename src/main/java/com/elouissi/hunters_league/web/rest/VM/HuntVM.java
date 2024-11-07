package com.elouissi.hunters_league.web.rest.VM;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HuntVM {
    @NotNull(message = "L'id de la participation est requis")
    private UUID participationID;
    @NotBlank(message = "Le nom du specie est requis")
    private String nameSpecie;
    @NotNull(message = "Le weight est requis")
    @PositiveOrZero(message = "Le weight doit être positif ou égal à zéro")
    private Double weight;
}

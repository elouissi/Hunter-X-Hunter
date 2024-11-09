package com.elouissi.hunters_league.service.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationDTO {

    @NotNull(message = "l'id est requis")
    private UUID id;


}

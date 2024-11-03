package com.elouissi.hunters_league.web.rest.VM;

import com.elouissi.hunters_league.domain.enums.Difficulty;
import com.elouissi.hunters_league.domain.enums.Role;
import com.elouissi.hunters_league.domain.enums.SpeciesType;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecieVM {



    @NotBlank(message = "name name is required")
    private String name;

    @NotBlank(message = "CIN is required")
    @Pattern(regexp = "^[0-9]+$", message = "CIN must be alphanumeric")
    private Long minimumWeight;

    @NotNull(message = "Role is required")
    private SpeciesType category;

    @NotNull(message = "difficulty is required")
    private Difficulty difficulty;
}

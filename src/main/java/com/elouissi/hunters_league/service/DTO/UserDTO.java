package com.elouissi.hunters_league.service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Username is required")
    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;







}

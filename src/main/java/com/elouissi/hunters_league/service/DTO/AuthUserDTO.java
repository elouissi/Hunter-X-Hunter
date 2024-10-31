package com.elouissi.hunters_league.service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class AuthUserDTO {

    @NotBlank(message = "Username is required")
    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;




    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}

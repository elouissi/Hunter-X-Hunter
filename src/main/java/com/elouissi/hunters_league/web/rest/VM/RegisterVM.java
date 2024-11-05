package com.elouissi.hunters_league.web.rest.VM;

import com.elouissi.hunters_league.domain.enums.Role;
import com.elouissi.hunters_league.validation.UniqueCin;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVM {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric")
    private String username;

    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @UniqueCin(message = "Cin est déja existe")
    @NotBlank(message = "CIN is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "CIN must be alphanumeric")
    private String cin;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Nationality is required")
    private String nationality;


    private LocalDateTime joinDate=LocalDateTime.now();

    @Future(message = "license Expiration date should be in future")
    private LocalDateTime licenseExpirationDate;

    @NotNull(message = "Role is required")
    private Role role;

}
package com.elouissi.hunters_league.validation;

import com.elouissi.hunters_league.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCinValidator implements ConstraintValidator<UniqueCin, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueCin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cin, ConstraintValidatorContext context) {
        return cin != null && !userRepository.existsByCin(cin);
    }
}

package com.elouissi.hunters_league.validation;

import com.elouissi.hunters_league.repository.CompetitonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCodeValidator implements ConstraintValidator<UniqueCode, String> {

    @Autowired
    private CompetitonRepository competitionRepository;

    @Override
    public void initialize(UniqueCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        return code != null && !competitionRepository.existsByCode(code);
    }
}

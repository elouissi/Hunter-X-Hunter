package com.elouissi.hunters_league.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MinDaysInFutureValidator implements ConstraintValidator<MinDaysInFuture, LocalDateTime> {

    private int days;

    @Override
    public void initialize(MinDaysInFuture constraintAnnotation) {
        this.days = constraintAnnotation.days();
    }

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }
        LocalDateTime now = LocalDateTime.now();
        long daysDifference = ChronoUnit.DAYS.between(now, date);
        return daysDifference >= days;
    }
}

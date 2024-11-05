package com.elouissi.hunters_league.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCinValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCin {
    String message() default "Le CIN doit être unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

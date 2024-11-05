package com.elouissi.hunters_league.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCodeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCode {
    String message() default "Code must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
